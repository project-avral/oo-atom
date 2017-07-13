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
package oo.atom.codegen.bytebuddy.task.equals;

import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import oo.atom.codegen.bytebuddy.task.utils.SmtAssumeTaskToGenerateBytecode;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtLoadReferenceTest {
    @Test
    public void loads0thArgumentOnStack() throws Exception {
        new SmtAssumeTaskToGenerateBytecode(
                new SmtLoadReference(0), 
                MethodVariableAccess.REFERENCE.loadFrom(0)
        ).check();
    }

    @Test
    public void loads5thArgumentOnStack() throws Exception {
        new SmtAssumeTaskToGenerateBytecode(
                new SmtLoadReference(5), 
                MethodVariableAccess.REFERENCE.loadFrom(5)
        ).check();
    }
}
