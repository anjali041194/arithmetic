package com.anjalitiwari.arithmetic.controller;

import java.util.ArrayList;
import java.util.List;

import com.anjalitiwari.arithmetic.model.Equation;
import com.anjalitiwari.arithmetic.repository.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EquationController {

  @Autowired
  CalculatorRepository calculatorRepository;

  @GetMapping("/equations")
  public ResponseEntity<List<Equation>> getAllEquations(@RequestParam(required = false) String expressions) {
    try {
      List<Equation> equations = new ArrayList<Equation>();

      if (expressions == null)
        calculatorRepository.findAll().forEach(equations::add);
      else
        calculatorRepository.findByExpression(expressions).forEach(equations::add);

      if (equations.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(equations, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/equations/{id}")
  public ResponseEntity<Equation> getEquationById(@PathVariable("id") long id) {
    Equation equation = calculatorRepository.findById(id);

    if (equation != null) {
      return new ResponseEntity<>(equation, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/equations")
  public ResponseEntity<String> createEquation(@RequestBody Equation equation) {
    try {
      calculatorRepository.save(new Equation(equation.getExpression(), equation.getResult()));
      return new ResponseEntity<>("Equation was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/equations/{id}")
  public ResponseEntity<String> deleteEquation(@PathVariable("id") long id) {
    try {
      int result = calculatorRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Equation with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Equation was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete equation.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/equations")
  public ResponseEntity<String> deleteAllEquations() {
    try {
      int numRows = calculatorRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " Equation(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete equations.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
