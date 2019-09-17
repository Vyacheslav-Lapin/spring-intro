package intro;

import static intro.common.TestUtils.fromSystemOutPrintln;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.luxoft.training.java.spring.intro.configs.ApplicationContext;
import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApplicationContext.class)
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ContextConfiguration("classpath:application-context.xml")
class AopAspectJTest {

  Bar bar;

  Customer customer;

  @NonFinal
  String extractedText;

  @BeforeEach
  void setUp() throws Exception {
    extractedText = fromSystemOutPrintln(
        () -> bar.sellSquishee(customer)
    );
  }

  @Test
  void testBeforeAdvice() {
    assertTrue("Before advice is not good enought", extractedText.contains("Hello"));
    assertTrue("Before advice is not good enought...", extractedText.contains("How are you doing?"));
  }

  @Test
  void testAfterAdvice() {
    assertTrue("After advice is not good enought...", extractedText.contains("Good Bye!"));
  }

  @Test
  void testAfterReturningAdvice() {
    assertTrue("Customer is broken", extractedText.contains("Good Enough?"));
    System.out.println(extractedText);
  }

  @Test
  void testAroundAdvice() {
    assertTrue("Around advice is not good enought...", extractedText.contains("Hi!"));
    assertTrue("Around advice is not good enought...", extractedText.contains("See you!"));
  }

  @Test
  void testAllAdvices() {
    assertFalse(bar instanceof ApuBar); // barObject instanceof ApuBar
  }
}
