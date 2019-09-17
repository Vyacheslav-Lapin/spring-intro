package lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Service
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer implements Person {

  @NonNull
  String name = "Вася";

  boolean broke;

}
