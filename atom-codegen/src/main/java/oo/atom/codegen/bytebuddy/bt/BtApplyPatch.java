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
package oo.atom.codegen.bytebuddy.bt;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.codegen.bytebuddy.matchers.IsAtom;
import oo.atom.codegen.bytebuddy.matchers.IsAtomAlias;
import oo.atom.task.result.TaskResultTransition;
import oo.atom.task.result.TrFailure;
import oo.atom.task.result.TrtConstant;
import oo.atom.task.result.TrtInferred;

class BtApplyPatchInference implements TaskResultTransition.Inference<Builder<?>, Builder<?>> {

    private final static ElementMatcher<TypeDescription> IS_ATOM = new IsAtom();
    private final static ElementMatcher<TypeDescription> IS_ATOM_ALIAS = new IsAtomAlias();

    private final TypeDescription type;

    public BtApplyPatchInference(TypeDescription type) {
        this.type = type;
    }

    @Override
    public final TaskResultTransition<Builder<?>, Builder<?>> taskResultTransition() {
        if (IS_ATOM_ALIAS.matches(type)) {
            return new BtApplyAtomAliasPatch(type);
        } else if (IS_ATOM.matches(type)) {
            return new BtApplyAtomPatch(type);
        } else {
            return new TrtConstant<>(
                new TrFailure<>(
                    String.format("%s is not atom", type.getName())
                )
            );
        }
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class BtApplyPatch extends TrtInferred<Builder<?>, Builder<?>> {

    public BtApplyPatch(TypeDescription type) {
        super(
            new BtApplyPatchInference(
                type
            )
        );
    }
}