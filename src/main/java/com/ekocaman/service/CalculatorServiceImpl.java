package com.ekocaman.service;

import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@Singleton
public class CalculatorServiceImpl implements CalculatorService {
    private static final Logger logger = Logger.getLogger(CalculatorServiceImpl.class.getName());

    public static final int DECIMAL_PRECISION = 10;

    private static final BigDecimal BIG_MAX_DOUBLE = BigDecimal.valueOf(Long.MAX_VALUE);
    private static final BigDecimal BIG_MIN_DOUBLE = BigDecimal.valueOf(Long.MIN_VALUE);

    private static BigDecimal doubleRangeCheck(BigDecimal val) {
        if (val.compareTo(BIG_MAX_DOUBLE) == 1 || val.compareTo(BIG_MIN_DOUBLE) == -1) {
            logger.log(Level.WARNING, "Calculation result overflow");
            throw new ArithmeticException("Double overflow");
        }

        return val;
    }

    @Override
    public BigDecimal add(BigDecimal firstNumber, BigDecimal secondNumber) {
        logger.log(Level.INFO, "CalculationService add(" + firstNumber + ", " + secondNumber + ")");
        return doubleRangeCheck(firstNumber.add(secondNumber));
    }

    @Override
    public BigDecimal subtract(BigDecimal firstNumber, BigDecimal secondNumber) {
        logger.log(Level.INFO, "CalculationService subtract(" + firstNumber + ", " + secondNumber + ")");
        return doubleRangeCheck(firstNumber.subtract(secondNumber));
    }

    @Override
    public BigDecimal multiply(BigDecimal firstNumber, BigDecimal secondNumber) {
        logger.log(Level.INFO, "CalculationService multiply(" + firstNumber + ", " + secondNumber + ")");
        return doubleRangeCheck(firstNumber.multiply(secondNumber));
    }

    @Override
    public BigDecimal divide(BigDecimal firstNumber, BigDecimal secondNumber) {
        logger.log(Level.INFO, "CalculationService divide(" + firstNumber + ", " + secondNumber + ")");
        return firstNumber.divide(secondNumber, DECIMAL_PRECISION, RoundingMode.HALF_UP);
    }
}
