package com.anjalitiwari.arithmetic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.AbstractMap;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class Equation {

    public enum Sign {PLUS, MINUS, MULTIPLICATION, DIVISION}

    final public static HashMap<Sign, String> SignSymbols = new HashMap<>() {{
        put(Sign.PLUS, "+");
        put(Sign.MINUS, "-");
        put(Sign.MULTIPLICATION, "x");
        put(Sign.DIVISION, ":");
    }};

    final public static Equation.Sign getSignBySymbol(String signSymbol) {
        return Equation.SignSymbols
                .entrySet()
                .stream()
                .filter(entry -> signSymbol.equals(entry.getValue()))
                .findFirst()
                .orElse(new AbstractMap.SimpleEntry<Sign, String>(null,null))
                .getKey();
    }

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("expression")
    private String expression;
    @JsonProperty("result")
    private Integer result;

    public Equation () {
        id = 1;
        expression = "dummy";
        result = 0;
    }

    public Equation ( Integer id, int number1, int number2, Sign sign, Integer result) {
        this.id = id;
        this.expression = Integer.toString(number1) + this.SignSymbols.get(sign) + Integer.toString(number2);
        this.result = result;
    }

    public Equation ( int number1, int number2, Sign sign, Integer result) {
        this.expression = Integer.toString(number1) + this.SignSymbols.get(sign) + Integer.toString(number2);
        this.result = result;
    }

    public Equation ( Integer id, String expression, Integer result) {
        this.id = id;
        this.expression = expression;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Expression [ " + expression + " ] = " + " Result: " + result;
    }
}
