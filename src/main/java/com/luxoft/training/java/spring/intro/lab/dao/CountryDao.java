package com.luxoft.training.java.spring.intro.lab.dao;

import com.luxoft.training.java.spring.intro.lab.model.Country;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.experimental.FieldDefaults;
import org.intellij.lang.annotations.Language;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
@FieldDefaults(makeFinal = true)
public class CountryDao extends NamedParameterJdbcDaoSupport {

  public static final String[][] COUNTRY_INIT_DATA = {
      {"Australia", "AU"},
      {"Canada", "CA"},
      {"France", "FR"},
      {"Hong Kong", "HK"},
      {"Iceland", "IC"},
      {"Japan", "JP"},
      {"Nepal", "NP"},
      {"Russian Federation", "RU"},
      {"Sweden", "SE"},
      {"Switzerland", "CH"},
      {"United Kingdom", "GB"},
      {"United States", "US"}};

  @Language("H2")
  private static final String LOAD = "insert into country (name, code_name) values (:name, :codeName)";
  @Language("H2")
  private static final String GET_ALL = "select * from country";
  @Language("H2")
  private static final String GET_BY_NAME_LIKE = "select * from country where name like :name";
  @Language("H2")
  private static final String GET_BY_NAME = "select * from country where name = :name";
  @Language("H2")
  private static final String GET_BY_CODE_NAME = "select * from country where code_name = :codeName";
  @Language("H2")
  private static final String UPDATE_NAME = "update country set name = :name where code_name = :codeName";

  private static final RowMapper<Country> COUNTRY_ROW_MAPPER =
      (rs, __) -> Country.builder()
                      .id(rs.getInt("id"))
                      .name(rs.getString("name"))
                      .codeName(rs.getString("code_name"))
                      .build();

  public CountryDao(DataSource dataSource) {
    setDataSource(dataSource);
  }

  public List<Country> getCountryList() {
    // TODO: implement it
    return null;
  }

  public List<Country> getCountryListStartWith(String name) {
    return Objects.requireNonNull(getNamedParameterJdbcTemplate())
               .query(GET_BY_NAME_LIKE,
                   Map.of("name", name + "%"),
                   COUNTRY_ROW_MAPPER);
  }

  public void updateCountryName(String codeName, String newCountryName) {
    // TODO: implement it
  }

  public void loadCountries() {
    var npjt = Objects.requireNonNull(getNamedParameterJdbcTemplate());
    for (String[] countryData : COUNTRY_INIT_DATA)
      npjt.update(LOAD, Map.of(countryData[0], countryData[1]));
  }

  public Country getCountryByCodeName(String codeName) {
    return Objects.requireNonNull(getNamedParameterJdbcTemplate())
               .queryForObject(
                   GET_BY_CODE_NAME,
                   Map.of("codeName", codeName),
                   COUNTRY_ROW_MAPPER);
  }

  public Country getCountryByName(String name) {
    return Optional.ofNullable(
        Objects.requireNonNull(getNamedParameterJdbcTemplate())
            .queryForObject(GET_BY_NAME, Map.of("name", name), COUNTRY_ROW_MAPPER))
               .orElseThrow(CountryNotFoundException::new);
  }
}
