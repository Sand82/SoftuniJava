package com.example.aop;

import org.springframework.stereotype.Component;

@Component
public class Student {

    public void satHello(){
        System.out.println("Hello!!!");
    }

    public void echo(String echo){
        System.out.println("ECHO: " + echo);
    }

    public String concat(String a, String b) {
        return  a + b ;
    }

    public void boom() throws IllegalAccessException {

        throw new IllegalAccessException("DON'T CALL ME!!! DO OT!!!");
    }

}
