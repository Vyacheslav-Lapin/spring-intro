package intro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lab.model.Country;
import lab.model.UsualPerson;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootTest(classes = com.luxoft.training.java.spring.intro.SpringIntroApplication.class)
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
class HelloWorldTest {

  protected static final Class<?> APPLICATION_CONTEXT_JAVA_CLASS_FILE_NAME =
      com.luxoft.training.java.spring.intro.configs.ApplicationContext.class;

  final BeanFactory context =
      new AnnotationConfigApplicationContext(
          APPLICATION_CONTEXT_JAVA_CLASS_FILE_NAME);

  @Test
  void testInitPerson() {
    assertEquals(
        getExpectedPerson(),
        context.getBean("person"));
  }

  @SuppressWarnings("WeakerAccess")
  protected static UsualPerson getExpectedPerson() {
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
