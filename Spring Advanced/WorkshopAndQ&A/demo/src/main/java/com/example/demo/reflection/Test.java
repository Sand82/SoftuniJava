package com.example.demo.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserDetails userDetails = new MyUserDetails()
                .setAge(40)
                .setName("Sand");

        System.out.println(test(userDetails, "Age"));
    }

    private static Object test(UserDetails userDetails, String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getProperty = userDetails.getClass().getMethod("get" + propertyName);

        return getProperty.invoke(userDetails);
    }
}
