package lab.model;

import lab.aop.AopLog;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ApuBar implements Bar {

  public Squishee sellSquishee(@NotNull Customer customer) {

    if (customer.isBroke())
      throw new CustomerBrokenException();

    AopLog.append("Here is your Squishee \n");
    return new Squishee("Usual Squishee");
  }
}
