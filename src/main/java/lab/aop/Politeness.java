package lab.aop;

import lab.model.Person;
import lab.model.Squishee;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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

  public void sayYouAreNotAllowed() {
    System.out.println("Hmmm...");
  }

  public void sayGoodBye() {
    System.out.println("Good Bye!");
  }

  public Object sayPoliteWordsAndSell(@NotNull ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("Hi!");
    Object retVal = pjp.proceed();
    System.out.println("See you!");
    return retVal;
  }

}
