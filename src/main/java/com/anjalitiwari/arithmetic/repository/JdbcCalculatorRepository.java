package com.anjalitiwari.arithmetic.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.anjalitiwari.arithmetic.model.Equation;

@Repository
public class JdbcCalculatorRepository implements CalculatorRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Integer save(Equation equation) {
    return jdbcTemplate.update("INSERT INTO equation (expression, result) VALUES(?,?)",
        new Object[] { equation.getExpression(), equation.getResult() });
  }

  @Override
  public Equation findById(Long id) {
    try {
      Equation equation= jdbcTemplate.queryForObject("SELECT * FROM equation WHERE id=?",
          BeanPropertyRowMapper.newInstance(Equation.class), id);

      return equation;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM equation WHERE id=?", id);
  }

  @Override
  public List<Equation> findAll() {
    return jdbcTemplate.query("SELECT * from equation", BeanPropertyRowMapper.newInstance(Equation.class));
  }

  @Override
  public List<Equation> findByExpression(String expression) {
    return jdbcTemplate.query("SELECT * from equation WHERE expression=?",
        BeanPropertyRowMapper.newInstance(Equation.class), expression);
  }


  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from equation");
  }
}
