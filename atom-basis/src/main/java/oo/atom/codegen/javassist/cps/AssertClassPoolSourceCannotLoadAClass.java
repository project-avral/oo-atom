package oo.atom.codegen.javassist.cps;

import javassist.NotFoundException;
import oo.atom.tests.Assertion;

import static org.assertj.core.api.Assertions.*;

/**
 * Asserts that attempt to load a certain class from
 * {@link javassist.ClassPool}, obtained from
 * {@link ClassPoolSource} under test, fails.
 *
 * @author Kapralov Sergey
 */
public class AssertClassPoolSourceCannotLoadAClass implements Assertion {
    private final ClassPoolSource source;
    private final String className;

    public AssertClassPoolSourceCannotLoadAClass(ClassPoolSource source, String className) {
        this.source = source;
        this.className = className;
    }

    @Override
    public final void check() throws Exception {
        assertThatThrownBy(() -> source.classPool().get(className)).isInstanceOf(NotFoundException.class);
    }
}