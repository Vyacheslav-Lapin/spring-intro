package com.luxoft.training.java.spring.intro.lab.dao;

import static java.util.Objects.requireNonNull;

import com.luxoft.training.java.spring.intro.lab.model.Country;
import java.util.List;
import java.util.Map;
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

  @Language("H2")
  private static final String GET_ALL = "select id, name, code_name from country";
  @Language("H2")
  private static final String GET_BY_NAME_LIKE = "select id, name, code_name from country where name like :name";
  @Language("H2")
  private static final String GET_BY_NAME = "select id, name, code_name from country where name = :name";
  @Language("H2")
  private static final String GET_BY_CODE_NAME = "select id, name, code_name from country where code_name = :codeName";
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
    return requireNonNull(getJdbcTemplate())
               .query(GET_ALL, COUNTRY_ROW_MAPPER);
  }

  public List<Country> getCountryListStartWith(String name) {
    return requireNonNull(getNamedParameterJdbcTemplate())
               .query(GET_BY_NAME_LIKE,
                   Map.of("name", name + "%"),
                   COUNTRY_ROW_MAPPER);
  }

  public void updateCountryName(String codeName, String newCountryName) {
    requireNonNull(getNamedParameterJdbcTemplate())
        .update(UPDATE_NAME,
            Map.of(
                "name", newCountryName,
                "codeName", codeName));
  }

  public Country getCountryByCodeName(String codeName) {
    return requireNonNull(getNamedParameterJdbcTemplate())
               .queryForObject(
                   GET_BY_CODE_NAME,
                   Map.of("codeName", codeName),
                   COUNTRY_ROW_MAPPER);
  }

  public Country getCountryByName(String name) {
    return Optional.ofNullable(
        requireNonNull(getNamedParameterJdbcTemplate())
            .queryForObject(GET_BY_NAME, Map.of("name", name), COUNTRY_ROW_MAPPER))
               .orElseThrow(CountryNotFoundException::new);
  }
}
