package org.example;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        StudentService service = (StudentService) Proxy.newProxyInstance(
                Main.class.getClassLoader(), new Class[]{StudentService.class}, new CacheableInvocationHandler(new StudentServiceImpl()));

        service.getAllStudents();
        service.getAllStudents();
    }
}

