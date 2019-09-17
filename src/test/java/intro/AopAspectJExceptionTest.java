package intro;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.luxoft.training.java.spring.intro.configs.ApplicationContext;
import intro.common.TestUtils;
import lab.model.Bar;
import lab.model.Customer;
import lab.model.CustomerBrokenException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@FieldDefaults(makeFinal = true)
@SpringBootTest(classes = ApplicationContext.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ContextConfiguration("classpath:application-context.xml")
class AopAspectJExceptionTest {

  Bar bar;

  Customer customer;

  @NonFinal
  String extractedText;

  @BeforeEach
  void setUp() throws Exception {
    extractedText = TestUtils.fromSystemOutPrintln(() -> {
//       customer.setBroke(true);
    });
  }

  @Test
  void testAfterThrowingAdvice() {

    assertThrows(CustomerBrokenException.class,
        () -> bar.sellSquishee(customer));

    assertTrue("Customer is not broken ",
        extractedText.contains("Hmmm..."));
  }
}
