package com.luxoft.training.java.spring.intro.lab.aop;

import com.luxoft.training.java.spring.intro.lab.model.Person;
import com.luxoft.training.java.spring.intro.lab.model.Squishee;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class Politeness {

  @Pointcut("execution(* sellSquishee(..))")
  private void sellSquishee(){
  }

  @Before("sellSquishee() && args(customer)")
  public void sayHello(@NotNull Person customer) {
    System.out.printf("Hello %s. How are you doing?\n", customer.getName());
  }

  @AfterReturning(
      pointcut = "sellSquishee()",
      returning = "retVal",
      argNames = "retVal")
  public void askOpinion(@NotNull Squishee retVal) {
    System.out.printf("Is %s Good Enough?\n", retVal.getName());
  }

  @AfterThrowing("sellSquishee()")
  public void sayYouAreNotAllowed() {
    System.out.println("Hmmm...");
  }

  @After("sellSquishee()")
  public void sayGoodBye() {
    System.out.println("Good Bye!");
  }

  @Around("sellSquishee()")
  public Object sayPoliteWordsAndSell(@NotNull ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("Hi!");
    Object retVal = pjp.proceed();
    System.out.println("See you!");
    return retVal;
  }

}
