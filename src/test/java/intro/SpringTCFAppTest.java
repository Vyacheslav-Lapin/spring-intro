package intro;

import static intro.HelloWorldTest.getExpectedPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.luxoft.training.java.spring.intro.configs.ApplicationContext;
import lab.model.Person;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApplicationContext.class)
@FieldDefaults(makeFinal = true)
//@ContextConfiguration("classpath:application-context.xml")
class SpringTCFAppTest {

	private final Person person;

	private final Person expectedPerson = getExpectedPerson();

	@Autowired
  public SpringTCFAppTest(Person person) {
    this.person = person;
  }

  @Test
  void testInitPerson() {
		assertEquals(expectedPerson, person);
	}

}
