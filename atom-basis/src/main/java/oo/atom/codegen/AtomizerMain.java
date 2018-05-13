/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package oo.atom.codegen;

import io.vavr.control.Option;
import oo.atom.anno.NotAtom;
import oo.atom.codegen.cp.CpFromString;
import oo.atom.codegen.stage.AggroInstrumentationStage;
import oo.atom.codegen.stage.CopyAtomAnnotations;
import oo.atom.codegen.stage.Stage;
import oo.atom.codegen.stage.StandardInstrumentationStage;
import oo.atom.instrumentation.ApplyStages;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Instrumentation entry point
 *
 * @author Kapralov Sergey
 */
@NotAtom
public class AtomizerMain {
    /**
     * Main.
     *
     * @param args CLI arguments
     * @throws Exception If something goes wrong.
     */
    public static final void main(String... args) throws Exception {
        final Stage stage;
        if(args.length > 0 && args[0].equals("-aggro")) {
            stage = new AggroInstrumentationStage();
        } else {
            stage = new StandardInstrumentationStage();
        }
        final Path workingDirectory = Option.of(System.getProperty("user.dir"))
                .map(Paths::get)
                .getOrElseThrow(RuntimeException::new);
        new ApplyStages(
            new CpFromString(
                System.getProperty("java.class.path")
            ),
            workingDirectory,
            stage,
            new CopyAtomAnnotations()
        ).apply();
    }
}
