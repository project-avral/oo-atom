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

import java.util.Objects;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Kapralov Sergey
 */
public class AssertAtomsAreNotEqual implements Assertion {
    private final String description;
    private final Object atom1;
    private final Object atom2;

    public AssertAtomsAreNotEqual(String description, Object atom1, Object atom2) {
        this.description = description;
        this.atom1 = atom1;
        this.atom2 = atom2;
    }
    
    @Override
    public final String description() {
        return description;
    }

    @Override
    public final void check() throws Exception {
        assertThat(atom1).isNotEqualTo(atom2);
    }

    @Override
    public final boolean equals(Object o) {
        if(o instanceof AssertAtomsAreNotEqual) {
            AssertAtomsAreNotEqual that = (AssertAtomsAreNotEqual)o;
            return Objects.equals(description, that.description) &&
                Objects.equals(atom1, that.atom1) &&
                Objects.equals(atom2, that.atom2);
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return Objects.hash(description, atom1, atom2);
    }
}
