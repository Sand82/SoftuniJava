package com.example.aop.modifying;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "true")
public class ModifyingAspect {

    @Pointcut("execution(* com.example.aop.Student.concat(..))")
    public void concatPointcut() {
    }

    @Around("concatPointcut() && args(a, b)")
    public Object modify(ProceedingJoinPoint pjp, String a, String b) throws Throwable {

       Object result = pjp.proceed(new Object[]{
                "[" + a + "]-",
                "[" + b + "]"
        });

        return "(" + result + ")";
    }
}
