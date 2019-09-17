package com.luxoft.training.java.spring.intro.aop;

import static com.luxoft.training.java.spring.intro.commons.TestUtils.fromSystemOutPrintln;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.luxoft.training.java.spring.intro.lab.model.ApuBar;
import com.luxoft.training.java.spring.intro.lab.model.Bar;
import com.luxoft.training.java.spring.intro.lab.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ContextConfiguration("classpath:application-context.xml")
class AopAspectJTest {

  Bar apuBar;

  Customer customer;

  @NonFinal
  String extractedText;

  @BeforeEach
  void setUp() throws Exception {
    extractedText = fromSystemOutPrintln(
        () -> apuBar.sellSquishee(customer)
    );
  }

  @Test
  void testBeforeAdvice() {
    assertTrue("Before advice is not good enough", extractedText.contains("Hello"));
    assertTrue("Before advice is not good enough...", extractedText.contains("How are you doing?"));
  }

  @Test
  void testAfterAdvice() {
    assertTrue("After advice is not good enough...", extractedText.contains("Good Bye!"));
  }

  @Test
  void testAfterReturningAdvice() {
    assertTrue("Customer is broken", extractedText.contains("Good Enough?"));
    System.out.println(extractedText);
  }

  @Test
  void testAroundAdvice() {
    assertTrue("Around advice is not good enough...", extractedText.contains("Hi!"));
    assertTrue("Around advice is not good enough...", extractedText.contains("See you!"));
  }

  @Test
  void testAllAdvices() {
    assertThat(apuBar instanceof ApuBar).isTrue();
  }
}
