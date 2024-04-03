package com.example.moodemo.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SimpleWindowTest {
    @Test
    public void testSimpleWindowImplementsInterface() {
        try {
            Class<?> simpleWindowClass = Class.forName("com.example.moodemo.ui.SimpleWindow");
            boolean implementsInterface = false;

            Class<?>[] interfaces = simpleWindowClass.getInterfaces();
            for (Class<?> iface : interfaces) {
                if (iface.equals(SimpleWindowInterface.class)) {
                    implementsInterface = true;
                    break;
                }
            }

            Assertions.assertTrue(implementsInterface, "SimpleWindowInterface is implemented by the SimpleWindow class.");
        } catch (ClassNotFoundException e) {
            fail("The SimpleWindow class was not found.", e);
        }
    }

    @Test
    public void testContinuePlayingYes() {
        SimpleWindow instance = mock(SimpleWindow.class);
        when(instance.continuePlaying("Do you want to continue playing?")).thenReturn(true);
        boolean result = instance.continuePlaying("Do you want to continue playing?");
        assertTrue(result);
    }

    @Test
    public void testContinuePlayingNo() {
        SimpleWindow instance = mock(SimpleWindow.class);
        when(instance.continuePlaying("Do you want to continue playing?")).thenReturn(false);
        boolean result = instance.continuePlaying("Do you want to continue playing?");
        assertFalse(result);
    }

}