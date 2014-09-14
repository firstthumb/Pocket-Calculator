package com.ekocaman.rest;

import com.ekocaman.model.CalculationResponse;
import com.ekocaman.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("calculator")
@Singleton
@Service
public class CalculatorResource {
    private static final Logger logger = Logger.getLogger(CalculatorResource.class.getName());

    @Autowired
    CalculatorService calculatorService;


    /**
     * This rest service takes two parameters and return the sum of these parameters
     * If the result is overflow, it returns Http Status Code 500
     * If the parameters are not number format, it returns Http 400
     * @param firstNumber
     * @param secondNumber
     * @return
     */
    @Path("/add/{first_number}/{second_number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResponse callAddService(@PathParam("first_number") double firstNumber, @PathParam("second_number") double secondNumber) {
        logger.log(Level.INFO, "AddService is called with Parameters first_number : " + firstNumber + " second_number : " + secondNumber);

        BigDecimal result = calculatorService.add(BigDecimal.valueOf(firstNumber), BigDecimal.valueOf(secondNumber));
        logger.log(Level.INFO, "Calculation Result : " + result);

        CalculationResponse calculationResponse = new CalculationResponse();
        calculationResponse.setValue(result.doubleValue());
        return calculationResponse;
    }

    /**
     * This rest service takes two parameters and subtracts second parameter from first parameter
     * If the result is overflow, it returns Http Status Code 500
     * If the parameters are not number format, it returns Http 400
     * @param firstNumber
     * @param secondNumber
     * @return
     */
    @Path("/subtract/{first_number}/{second_number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResponse callSubtractService(@PathParam("first_number") double firstNumber, @PathParam("second_number") double secondNumber) {
        logger.log(Level.INFO, "SubtractService is called with Parameters first_number : " + firstNumber + " second_number : " + secondNumber);

        BigDecimal result = calculatorService.subtract(BigDecimal.valueOf(firstNumber), BigDecimal.valueOf(secondNumber));
        logger.log(Level.INFO, "Calculation Result : " + result);

        CalculationResponse calculationResponse = new CalculationResponse();
        calculationResponse.setValue(result.doubleValue());
        return calculationResponse;
    }

    /**
     * This rest service takes two parameters and multiples first parameter and second parameter
     * If the result is overflow, it returns Http Status Code 500
     * If the parameters are not number format, it returns Http 400
     * @param firstNumber
     * @param secondNumber
     * @return
     */
    @Path("/multiply/{first_number}/{second_number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResponse callMultiplyService(@PathParam("first_number") double firstNumber, @PathParam("second_number") double secondNumber) {
        logger.log(Level.INFO, "MultiplyService is called with Parameters first_number : " + firstNumber + " second_number : " + secondNumber);

        BigDecimal result = calculatorService.multiply(BigDecimal.valueOf(firstNumber), BigDecimal.valueOf(secondNumber));
        logger.log(Level.INFO, "Calculation Result : " + result);

        CalculationResponse calculationResponse = new CalculationResponse();
        calculationResponse.setValue(result.doubleValue());
        return calculationResponse;
    }

    /**
     * This rest service takes two parameters and divides first parameter by second parameter
     * If divide by zero exists, it returns Http Status Code 500
     * If the parameters are not number format, it returns Http 400
     * @param firstNumber
     * @param secondNumber
     * @return
     */
    @Path("/divide/{first_number}/{second_number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResponse callDivideService(@PathParam("first_number") double firstNumber, @PathParam("second_number") double secondNumber) {
        logger.log(Level.INFO, "DivideService is called with Parameters first_number : " + firstNumber + " second_number : " + secondNumber);

        BigDecimal result = calculatorService.divide(BigDecimal.valueOf(firstNumber), BigDecimal.valueOf(secondNumber));
        logger.log(Level.INFO, "Calculation Result : " + result);

        CalculationResponse calculationResponse = new CalculationResponse();
        calculationResponse.setValue(result.doubleValue());
        return calculationResponse;
    }
}
