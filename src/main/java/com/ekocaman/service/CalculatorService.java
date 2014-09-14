package com.ekocaman.service;

import java.math.BigDecimal;

/**
 * This class does simple calculations like add, subtract, multiply and divide
 */
public interface CalculatorService {

    /**
     * This function adds two decimal numbers and return the result
     * @param firstNumber decimal number, not null
     * @param secondNumber decimal number, not null
     * @throws ArithmeticException if overflow exists
     * @return BigDecimal
     */
    public BigDecimal add(BigDecimal firstNumber, BigDecimal secondNumber);

    /**
     * This function subtracts two decimal numbers and return the result
     * @param firstNumber decimal number, not null
     * @param secondNumber decimal number, not null
     * @throws ArithmeticException if overflow exists
     * @return BigDecimal
     */
    public BigDecimal subtract(BigDecimal firstNumber, BigDecimal secondNumber);

    /**
     * This function multiplies two decimal numbers and return the result
     * @param firstNumber decimal number, not null
     * @param secondNumber decimal number, not null
     * @throws ArithmeticException if overflow exists
     * @return BigDecimal
     */
    public BigDecimal multiply(BigDecimal firstNumber, BigDecimal secondNumber);

    /**
     * This function divides two decimal numbers and return the result
     * @param firstNumber decimal number, not null
     * @param secondNumber decimal number, not null
     * @throws ArithmeticException if the exact quotient does not have a
     *         terminating decimal expansion
     * @return BigDecimal
     */
    public BigDecimal divide(BigDecimal firstNumber, BigDecimal secondNumber);
}
