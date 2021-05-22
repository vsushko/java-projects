package com.vsushko;

import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author vsushko
 */
class MockableTypes {

    @Test
    void testFinalClass() {
        // final class
        final MockitoException exception = assertThrows(
                MockitoException.class,
                () -> mock(FinalClass.class)
        );

        assertThat(exception.getClass(), is(equalTo(MockitoException.class)));
    }

    @Test
    void testAbstractClass() {
        final AbstractClass abstractClass = mock(AbstractClass.class);
        abstractClass.concreteMethod();
        verify(abstractClass).concreteMethod();

        when(abstractClass.abstractMethod()).thenReturn(42);
    }

    @Test
    void testConcreteClass() {
        final ConcreteClass concreteClass = mock(ConcreteClass.class);
        concreteClass.instanceMethod();
        concreteClass.finalMethod();
        concreteClass.privateMethod();

        // verify(concreteClass).instanceMethod();
        // verify(concreteClass).finalMethod();
        // verify(concreteClass).privateMethod();
    }

    @Test
    void testInterface() {
        final MyInterface myInterface = mock(MyInterface.class);

        myInterface.defaultMethod();
        myInterface.abstractMethod();

        verify(myInterface).defaultMethod();
        verify(myInterface).abstractMethod();
    }

    interface MyInterface {
        static void staticMethod() {
            System.out.println("myInterface.staticMethod");
        }

        default void defaultMethod() {
            System.out.println("myInterface.defaultMethod");
        }

        void abstractMethod();
    }

    /**
     * Inner types declarations
     */
    static final class FinalClass {
        private void foo() {
            System.out.println("MyFinalClass.foo");
        }
    }

    static class ConcreteClass {
        static void staticMethod() {
            System.out.println("ConcreteClass.staticMethod");
        }

        static void staticMethod2() {
            System.out.println("ConcreteClass.staticMethod2");
        }

        void instanceMethod() {
            System.out.println("ConcreteClass.instanceMethod");
        }

        final void finalMethod() {
            System.out.println("ConcreteClass.finalMethod");
        }

        private void privateMethod() {
            System.out.println("ConcreteClass.privateMethod");
        }
    }

    abstract class AbstractClass {
        void concreteMethod() {
            System.out.println("AbstractClass.concreteMethod");
        }

        abstract int abstractMethod();
    }
}
