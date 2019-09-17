package com.luxoft.training.java.spring.intro;

import java.util.List;
import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringIntroApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringIntroApplication.class, args);
  }

}
