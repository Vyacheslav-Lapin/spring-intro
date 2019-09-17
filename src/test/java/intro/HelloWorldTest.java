package intro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest(classes = com.luxoft.training.java.spring.intro.SpringIntroApplication.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class HelloWorldTest {

  protected static final String APPLICATION_CONTEXT_XML_FILE_NAME =
      "application-context.xml";

  final BeanFactory context =
      new ClassPathXmlApplicationContext(
      APPLICATION_CONTEXT_XML_FILE_NAME);

  @Test
  void testInitPerson() {
    assertEquals(getExpectedPerson(), context.getBean("person"));
  }

  @SuppressWarnings("WeakerAccess")
  protected UsualPerson getExpectedPerson() {
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
