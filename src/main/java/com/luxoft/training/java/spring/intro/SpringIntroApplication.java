package com.luxoft.training.java.spring.intro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringIntroApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
        SpringApplication.run(SpringIntroApplication.class, args);

    System.out.println(
        context.getBeanDefinitionCount());

  }

}
