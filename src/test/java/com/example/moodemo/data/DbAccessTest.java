package com.example.moodemo.data;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class DbAccessTest {

    @Test
    void testIfClassExists() {
        try {
            Class<?> clazz = Class.forName("com.example.moodemo.data.DbAccess");
            assertNotNull(clazz, "The DbAccess class should exist.");
        } catch (ClassNotFoundException e) {
            fail("The DbAccess class does not exist.", e);
        }
    }

    @Test
    void testExecuteQuery() {
        try {
            Class<?> clazz = Class.forName("com.example.moodemo.data.DbAccess");
            Method method = clazz.getMethod("executeQuery", String.class);
            assertNotNull(method, "There should be a method called executeQuery in the DbAccess class.");
        } catch (ClassNotFoundException e) {
            fail("The DbAccess class does not exist.", e);
        } catch (NoSuchMethodException e) {
            fail("There should be a method called executeQuery in the DbAccess class.", e);
        }
    }

}