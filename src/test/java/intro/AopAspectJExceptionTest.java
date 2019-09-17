package intro;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import lab.aop.AopLog;
import lab.model.Bar;
import lab.model.Customer;
import lab.model.CustomerBrokenException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration("classpath:application-context.xml")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class AopAspectJExceptionTest {

  Bar bar;

  Customer customer;

  @BeforeEach
  void setUp() throws Exception {

    //        customer.setBroke(true);
  }

  @Test
  void testAfterThrowingAdvice() {

    assertThrows(CustomerBrokenException.class,
        () -> bar.sellSquishee(customer));

    assertTrue("Customer is not broken ", AopLog.getStringValue().contains("Hmmm..."));
    System.out.println(AopLog.getStringValue());
  }
}
