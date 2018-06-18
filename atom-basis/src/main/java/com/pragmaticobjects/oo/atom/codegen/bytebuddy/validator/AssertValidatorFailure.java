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

package com.pragmaticobjects.oo.atom.codegen.bytebuddy.validator;

import com.pragmaticobjects.oo.atom.tests.Assertion;
import io.vavr.collection.List;
import net.bytebuddy.description.type.TypeDescription;
import org.assertj.core.api.Assertions;


/**
 * Asserts that validator fails on certain {@link TypeDescription}, with expected issues
 *
 * @author Kapralov Sergey
 */
public class AssertValidatorFailure implements Assertion {
    private final Validator validator;
    private final TypeDescription type;
    private final List<String> issues;

    /**
     * Ctor.
     *
     * @param validator The validator under the test.
     * @param type Type to validate.
     * @param issues List of expected issues.
     */
    public AssertValidatorFailure(final Validator validator, final TypeDescription type, List<String> issues) {
        this.validator = validator;
        this.type = type;
        this.issues = issues;
    }

    /**
     * Ctor.
     *
     * @param validator The validator under the test.
     * @param type Type to validate.
     * @param issues List of expected issues.
     */
    public AssertValidatorFailure(final Validator validator, final TypeDescription type, String... issues) {
        this(
            validator,
            type,
            List.of(issues)
        );
    }

    @Override
    public final void check() throws Exception {
        Assertions.assertThat(validator.errors(type)).containsExactly(issues.toJavaArray(String.class));
    }
}
