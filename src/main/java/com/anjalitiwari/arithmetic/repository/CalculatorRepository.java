package com.anjalitiwari.arithmetic.repository;

import java.util.List;

import com.anjalitiwari.arithmetic.model.Equation;

public interface CalculatorRepository {
  Integer save(Equation equation);

  Equation findById(Long id);

  int deleteById(Long id);

  List<Equation> findAll();

  List<Equation> findByExpression(String expression);

  int deleteAll();
}
