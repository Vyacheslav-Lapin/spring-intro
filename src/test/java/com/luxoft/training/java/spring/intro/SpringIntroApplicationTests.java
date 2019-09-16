package com.luxoft.training.java.spring.intro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import com.luxoft.training.java.spring.intro.dao.CatRepository;
import com.luxoft.training.java.spring.intro.model.Cat;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class SpringIntroApplicationTests {

  final MockMvc mvc;

  final CatRepository catRepository;

  @BeforeEach
  void setUp() {
    Stream.of("Barsik", "Vasya", "Murzik")
        .map(Cat::new)
        .forEach(catRepository::save);
  }

  @Test
  void contextLoads() {
  }

  @Test
  @SneakyThrows
  void catsPresented() {
    mvc.perform(get("/cats"))
		.andExpect(status().isOk())
		.andExpect(content()
			.contentType(HAL_JSON_UTF8_VALUE))
		.andExpect(
			mvcResult -> assertEquals(3,
    				JsonPath.parse(mvcResult.getResponse().getContentAsString())
        				.<Integer>read("$.page.totalElements").intValue()));
  }
}
