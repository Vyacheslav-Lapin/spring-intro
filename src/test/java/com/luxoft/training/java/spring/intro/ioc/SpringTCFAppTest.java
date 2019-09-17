package com.luxoft.training.java.spring.intro.ioc;

import static com.luxoft.training.java.spring.intro.ioc.HelloWorldTest.getExpectedPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.luxoft.training.java.spring.intro.lab.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ContextConfiguration("classpath:application-context.xml")
class SpringTCFAppTest {

	Person person;

	Person expectedPerson = getExpectedPerson();

  @Test
  void testInitPerson() {
		assertEquals(expectedPerson, person);
	}

}
