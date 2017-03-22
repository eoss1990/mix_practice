package com.seeyon.SpringBean.aop;

import org.aspectj.lang.annotation.*;

/**
 * Created by yangyu on 16/10/12.
 */
@Aspect
public class Logging {

    @Pointcut("execution(* com.seeyon.SpringBean.aop.Student.get*(..))")
    public void getMethod(){};

    /**
     * This is the method which I would like to execute
     * before a selected method execution.
     */
    @Before("getMethod()")
    public void beforeAdvice() {
        System.out.println("Going to setup student profile.");
    }

    /**
     * This is the method which I would like to execute
     * after a selected method execution.
     */
    @After("getMethod()")
    public void afterAdvice() {
        System.out.println("Student profile has been setup.");
    }

    /**
     * This is the method which I would like to execute
     * when any method returns.
     */
    @AfterReturning(pointcut = "getMethod()",returning = "retVal")
    public void afterReturningAdvice(Object retVal) {
        System.out.println("Returning:" + retVal.toString());
    }

    /**
     * This is the method which I would like to execute
     * if there is an exception raised.
     */
    @AfterThrowing(pointcut = "getMethod()",throwing = "ex")
    public void AfterThrowingAdvice(IllegalArgumentException ex) {
        System.out.println("There has been an exception: " + ex.toString());
    }
}
