package com.luxoft.training.java.spring.intro.aop;

import static com.luxoft.training.java.spring.intro.commons.TestUtils.fromSystemOutPrintln;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.luxoft.training.java.spring.intro.lab.model.Bar;
import com.luxoft.training.java.spring.intro.lab.model.Customer;
import com.luxoft.training.java.spring.intro.lab.model.CustomerBrokenException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ContextConfiguration("classpath:application-context.xml")
class AopAspectJExceptionTest {

  Bar bar;

  Customer customer;

  @NonFinal
  String extractedText;

  @BeforeEach
  void setUp() throws Exception {
    customer.setBroke(true);
  }

  @Test
  void testAfterThrowingAdvice() {

    extractedText = fromSystemOutPrintln(
        () -> assertThrows(CustomerBrokenException.class,
            () -> bar.sellSquishee(customer)));

    assertTrue("Customer is not broken ",
        extractedText.contains("Hmmm..."));
  }

  @AfterEach
  void tearDown() {
    customer.setBroke(false);
  }
}
