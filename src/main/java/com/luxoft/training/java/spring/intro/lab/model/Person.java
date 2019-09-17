package com.luxoft.training.java.spring.intro.lab.model;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

//@Slf4j
public interface Person {

  Logger log = org.slf4j.LoggerFactory.getLogger(Person.class);

  Person setName(String name);
  String getName ();

  default void sayHello(@NotNull Person person) {
    log.info("Hello, {}, I'm {}", person.getName(), getName());
  }
}
