package com.vsushko;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author vsushko
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MockableTypes.ConcreteClass.class)
public class StaticTypeMocking {

    @Test
    public void testMethod() {
        PowerMockito.verifyStatic(MockableTypes.ConcreteClass.class);
        MockableTypes.ConcreteClass.staticMethod();
    }
}
