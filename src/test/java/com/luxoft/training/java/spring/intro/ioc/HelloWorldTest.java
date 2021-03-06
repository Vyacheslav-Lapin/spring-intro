package com.luxoft.training.java.spring.intro.ioc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.luxoft.training.java.spring.intro.lab.model.Country;
import com.luxoft.training.java.spring.intro.lab.model.UsualPerson;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(makeFinal = true)
class HelloWorldTest {

  protected static final Class<?> APPLICATION_CONTEXT_JAVA_CLASS_FILE_NAME =
      com.luxoft.training.java.spring.intro.configs.ApplicationContext.class;

  BeanFactory context =
      new AnnotationConfigApplicationContext(
          APPLICATION_CONTEXT_JAVA_CLASS_FILE_NAME);

  @Test
  void testInitPerson() {
    assertEquals(
        getExpectedPerson(),
        context.getBean("person"));
  }

  static UsualPerson getExpectedPerson() {
    return UsualPerson.builder()
               .id(1)
               .age(35)
               .name("John Smith")
               .country(Country.builder()
                            .id(1)
                            .name("Russia")
                            .codeName("RU")
                            .build())
               .height(1.72f)
               .isProgrammer(true)
//               .contacts(List.of("222-33-22", "kjhgdfg@jhdf.ru"))
               .contact("222-33-22")
               .contact("kjhgdfg@jhdf.ru")
               .build();
  }

}
