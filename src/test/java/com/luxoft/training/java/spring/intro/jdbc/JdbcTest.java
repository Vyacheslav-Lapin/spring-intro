package com.luxoft.training.java.spring.intro.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import com.luxoft.training.java.spring.intro.lab.dao.CountryDao;
import com.luxoft.training.java.spring.intro.lab.model.Country;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ContextConfiguration("classpath:application-context.xml")
class JdbcTest {

  CountryDao countryDao;

  List<Country> expectedCountryList = new ArrayList<>();
  List<Country> expectedCountryListStartsWithA = new ArrayList<>();
  Country countryWithChangedName = new Country(1, "Russia", "RU");

  @BeforeEach
  void setUp() {
    initExpectedCountryLists();
    countryDao.loadCountries();
  }

  @Test
  @DirtiesContext
  void testCountryList() {
    List<Country> countryList = countryDao.getCountryList();
    assertThat(countryList).isNotNull();
    assertThat(expectedCountryList.size()).isEqualTo(countryList.size());
    for (int i = 0; i < expectedCountryList.size(); i++) {
      assertThat(expectedCountryList.get(i)).isEqualTo(countryList.get(i));
    }
  }

  @Test
  @DirtiesContext
  void testCountryListStartsWithA() {
    List<Country> countryList = countryDao.getCountryListStartWith("A");
    assertThat(countryList).isNotNull();
    assertThat(expectedCountryListStartsWithA.size()).isEqualTo(countryList.size());
    for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
      assertThat(expectedCountryListStartsWithA.get(i)).isEqualTo(countryList.get(i));
    }
  }

  @Test
  @DirtiesContext
  void testCountryChange() {
    countryDao.updateCountryName("RU", "Russia");
    assertThat(countryDao.getCountryByCodeName("RU").getName())
        .isEqualTo("Russia");
  }

  private void initExpectedCountryLists() {
    for (int i = 0; i < CountryDao.COUNTRY_INIT_DATA.length; i++) {
      String[] countryInitData = CountryDao.COUNTRY_INIT_DATA[i];
      Country country = new Country(i, countryInitData[0], countryInitData[1]);
      expectedCountryList.add(country);
      if (country.getName().startsWith("A")) {
        expectedCountryListStartsWithA.add(country);
      }
    }
  }
}
