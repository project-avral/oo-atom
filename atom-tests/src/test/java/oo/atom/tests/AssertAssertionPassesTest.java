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
package oo.atom.tests;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 * Tests suite for {@link AssertAssertionPasses}
 * 
 * @author Kapralov Sergey
 */
public class AssertAssertionPassesTest {
    @Test
    public final void hasDescription() {
        final Assertion assertion = new AssertAssertionPasses("has description", new AssertFail("FAIL"));
        assertThat(assertion.description()).isEqualTo("has description");
    }
    
    @Test
    public final void failsOnFailedAssertion() {
        final Assertion assertion = new AssertAssertionPasses("fails on failed assertion", new AssertFail("FAIL"));
        assertThatThrownBy(() -> assertion.check()).isInstanceOf(AssertionError.class);
    }

    @Test
    public final void succeedsOnPassingAssertion() {
        final Assertion assertion = new AssertAssertionPasses("succeeds on passing assertion", new AssertPass("FAIL"));
        assertThatCode(() -> assertion.check()).doesNotThrowAnyException();
    }
}
