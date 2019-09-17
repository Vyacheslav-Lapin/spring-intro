package com.luxoft.training.java.spring.intro.configs;

import com.luxoft.training.java.spring.intro.lab.model.Country;
import com.luxoft.training.java.spring.intro.lab.model.Person;
import com.luxoft.training.java.spring.intro.lab.model.UsualPerson;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.luxoft.training.java.spring.intro.lab")
//@ImportResource("classpath:application-context.xml")
public class ApplicationContext {

  @Bean
  public Person person(Country country, List<String> contacts) {
    return UsualPerson.builder()
               .id(1)
               .name("John Smith")
               .isProgrammer(true)
               .height(1.72f)
               .age(35)
               .country(country)
               .contacts(contacts)
               .build();
  }

  @Bean
  public Country country() {
    return Country.builder()
               .id(1)
               .name("Russia")
               .codeName("RU")
               .build();
  }

  @Bean
  public List<String> contacts() {
    return List.of("222-33-22", "kjhgdfg@jhdf.ru");
  }
}
