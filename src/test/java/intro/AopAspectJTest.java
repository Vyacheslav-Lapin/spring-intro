package intro;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import lab.aop.AopLog;
import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration("classpath:application-context.xml")
class AopAspectJTest {

  Bar bar;

  Customer customer;

  @BeforeEach
  void setUp() throws Exception {
    bar.sellSquishee(customer);
  }

  @Test
  void testBeforeAdvice() {
    assertTrue("Before advice is not good enought...", AopLog.getStringValue().contains("Hello"));
    assertTrue("Before advice is not good enought...", AopLog.getStringValue().contains("How are you doing?"));
    System.out.println(AopLog.getStringValue());
  }

  @Test
  void testAfterAdvice() {
    System.out.println(AopLog.getStringValue());
    assertTrue("After advice is not good enought...", AopLog.getStringValue().contains("Good Bye!"));
  }

  @Test
  void testAfterReturningAdvice() {
    assertTrue("Customer is broken", AopLog.getStringValue().contains("Good Enough?"));
    System.out.println(AopLog.getStringValue());
  }

  @Test
  void testAroundAdvice() {
    assertTrue("Around advice is not good enought...", AopLog.getStringValue().contains("Hi!"));
    assertTrue("Around advice is not good enought...", AopLog.getStringValue().contains("See you!"));
    System.out.println(AopLog.getStringValue());
  }

  @Test
  void testAllAdvices() {
    assertFalse(bar instanceof ApuBar); // barObject instanceof ApuBar
  }
}
