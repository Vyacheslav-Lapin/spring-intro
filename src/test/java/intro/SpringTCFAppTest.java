package intro;

import static intro.HelloWorldTest.getExpectedPerson;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lab.model.Person;
import lab.model.UsualPerson;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ContextConfiguration("classpath:application-context.xml")
class SpringTCFAppTest {

	UsualPerson person;

	Person expectedPerson = getExpectedPerson();

	@Test
  void testInitPerson() {
		assertEquals(expectedPerson, person);
	}

}
