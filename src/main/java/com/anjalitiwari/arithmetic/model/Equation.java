package com.anjalitiwari.arithmetic.model;

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
    private long id;
    private String expression;
    private double result;

    public Equation ( long id, int number1, int number2, Sign sign, double result) {
        this.id = id;
        this.expression = Integer.toString(number1) + this.SignSymbols.get(sign) + Integer.toString(number2);
        this.result = result;
    }

    public Equation ( int number1, int number2, Sign sign, double result) {
        this.id = id;
        this.expression = Integer.toString(number1) + this.SignSymbols.get(sign) + Integer.toString(number2);
        this.result = result;
    }

    public Equation ( String expression, double result) {
        this.expression = expression;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Expression [ " + expression + " ] = " + " Result: " + Double.toString(result);
    }
}
