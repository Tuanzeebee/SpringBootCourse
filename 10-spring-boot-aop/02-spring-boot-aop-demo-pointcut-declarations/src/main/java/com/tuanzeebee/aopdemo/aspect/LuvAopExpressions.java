package com.tuanzeebee.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

    @Pointcut("execution(* com.tuanzeebee.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    //create a pointcut for getter setter method
    @Pointcut("execution(* com.tuanzeebee.aopdemo.dao.*.get*(..))")
    public void getter(){}

    @Pointcut("execution(* com.tuanzeebee.aopdemo.dao.*.set*(..))")
    public void setter(){}

    //create a pointcut include package ... exclude getter/setter

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}
}
