package com.luxoft.training.java.spring.intro.lab.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder(toBuilder = true)
@AllArgsConstructor
//@Entity
public class UsualPerson implements Person {

  //@Id
  int id;

  String name;

//  @ManyToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "country_id")
  Country country;

  int age;
  float height;
  boolean isProgrammer;

  @Singular
  List<String> contacts;
}
