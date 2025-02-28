package com.tuanzeebee.aopdemo.aspect;

import com.tuanzeebee.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.tuanzeebee.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        //print out method we are advising on
        //print out which method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();

        System.out.println("\n=====>>> Executing @Around on method: "+method);

        //get begin timestamp
        //long begin = System.currentTimeMillis();
        long begin = System.nanoTime();

        //now,let's execute the method
//        Object result = null;
//        try {
//            result = theProceedingJoinPoint.proceed();
//        } catch (Exception exc) {
//            System.out.println(exc.getMessage());
//
//            result = "Major accident! But no worries ,your private AOP helicopter is on the way!";
//        }
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            //rethrow exception

            throw exc;
        }

        //get end timestamp
        //long end = System.currentTimeMillis();
        long end = System.nanoTime();

        //compute duration and display it
        long duration = end-begin;
        System.out.println("\n======>>>Duration: "+duration+" nanoseconds");

        return result;
    }

    @After("execution(* com.tuanzeebee.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();

        System.out.println("\n=====>>> Executing @After on method: "+method);

    }

    @AfterThrowing(
            pointcut = "execution(* com.tuanzeebee.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint,Throwable theExc){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toString();

        System.out.println("\n=====>>> Executing @AfterThrowing on method: "+method);
        //log the exception

        System.out.println("\n=====>>> The exception is: "+theExc);
    }

    @AfterReturning(
            pointcut = "execution(* com.tuanzeebee.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        //print out which method we are advising on

        String method = theJoinPoint.getSignature().toString();

        System.out.println("\n=====>>> Executing @AfterReturning on method: "+method);

        //print out the result of the method call

        System.out.println("\n=====>>> Result is: "+result);

        // let's post-process the data ... let's modify it :-)

        //convert the account name to uppercase

        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> Result is: "+result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        //loop through accounts

        for (Account tempAccount : result){
            String theUpperName = tempAccount.getName().toUpperCase();

            tempAccount.setName(theUpperName);
        }

        //get uppercase version of name
        //update the name on the account
    }

    @Before("com.tuanzeebee.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=======>>> Executing @Before advice on");

        //display the method signature

        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method :" +methodSignature);

        //display method arguments

        //get args
        Object[] args = theJoinPoint.getArgs();
        //loop thru args
        for (Object tempArgs : args){
            System.out.println(tempArgs);
            if (tempArgs instanceof Account){

                //downcast and print Account specific stuff
                Account theAccount =(Account) tempArgs;
                System.out.println("Account name: "+theAccount.getName());
                System.out.println("Account level: "+theAccount.getLevel());
            }
        }
    }
}
