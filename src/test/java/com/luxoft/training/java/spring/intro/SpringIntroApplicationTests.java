package com.luxoft.training.java.spring.intro;

import static org.junit.Assert.assertEquals;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import com.luxoft.training.java.spring.intro.dao.CatRepository;
import com.luxoft.training.java.spring.intro.model.Cat;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
public class SpringIntroApplicationTests {

  @Autowired
  MockMvc mvc;

  @Autowired
  CatRepository catRepository;

  @Before
  public void setUp() {
    Stream.of("Barsik", "Vasya", "Murzik")
        .map(Cat::new)
        .forEach(catRepository::save);
  }

  @Test
  public void contextLoads() {
  }

  @Test
  @SneakyThrows
  public void catsPresented() {
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
