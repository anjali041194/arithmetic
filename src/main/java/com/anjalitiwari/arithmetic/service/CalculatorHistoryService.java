package com.anjalitiwari.arithmetic.service;

import com.anjalitiwari.arithmetic.model.Equation;
import com.anjalitiwari.arithmetic.repository.CalculatorRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculatorHistoryService {

    private CalculatorRepository calculatorHistoryRepository;

    public CalculatorHistoryService(CalculatorRepository calculatorHistoryRepository) {
        this.calculatorHistoryRepository = calculatorHistoryRepository;
    }

    public Integer save(Equation equation) {
        return calculatorHistoryRepository.save(equation);
    }

}
