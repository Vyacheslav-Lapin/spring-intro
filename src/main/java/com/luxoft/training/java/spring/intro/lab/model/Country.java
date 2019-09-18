package com.luxoft.training.java.spring.intro.lab.model;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

//@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Country {

  @Exclude
  int id;

  @NonNull
  String name;

  @NonNull
  String codeName;
}
