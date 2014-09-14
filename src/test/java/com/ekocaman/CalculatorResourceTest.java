package com.ekocaman;

import com.ekocaman.config.ArithmeticExceptionMapper;
import com.ekocaman.config.ParameterExceptionMapper;
import com.ekocaman.model.CalculationResponse;
import com.ekocaman.rest.CalculatorResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CalculatorResourceTest extends JerseyTest {
    private static final double DELTA = 1e-15;

    @Override
    protected Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringAnnotationConfig.class);
        ResourceConfig config = new ResourceConfig(CalculatorResource.class).property("contextConfig", context);
        config.register(ParameterExceptionMapper.class);
        config.register(ArithmeticExceptionMapper.class);
        return config;
    }

    // Addition Service Test Cases

    @Test
    public void testAddRestServiceSimple() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("add/1/3").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(4.0, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testAddRestServiceWithDecimal() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("add/1.1/3.3").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(4.4, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testAddRestServiceWithIllegalParameters() {
        final WebTarget target = target("calculator");

        try {
            target.path("add/1s/3").request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (BadRequestException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }

    @Test
    public void testAddRestServiceWithOverflowResult() {
        final WebTarget target = target("calculator");

        try {
            String firstParam = String.valueOf(Long.MAX_VALUE);
            String secondParam = String.valueOf(Long.MAX_VALUE);
            target.path("add/" + firstParam + "/" + secondParam).request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (InternalServerErrorException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }

    // Subtract Service Test Cases

    @Test
    public void testSubtractRestServiceSimple() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("subtract/1/3").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(-2.0, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testSubtractRestServiceWithDecimal() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("subtract/1.1/3.3").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(-2.2, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testSubtractRestServiceWithIllegalParameters() {
        final WebTarget target = target("calculator");

        try {
            target.path("subtract/1s/3").request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (BadRequestException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }

    @Test
    public void testSubtractRestServiceWithOverflowResult() {
        final WebTarget target = target("calculator");

        try {
            String firstParam = String.valueOf(Long.MIN_VALUE);
            String secondParam = String.valueOf(Long.MAX_VALUE);
            target.path("subtract/" + firstParam + "/" + secondParam).request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (InternalServerErrorException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }

    // Multiply Service Test Cases

    @Test
    public void testMultiplyRestServiceSimple() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("multiply/1/3").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(3.0, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testMultiplyRestServiceWithDecimal() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("multiply/1.1/3.3").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(3.63, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testMultiplyRestServiceWithIllegalParameters() {
        final WebTarget target = target("calculator");

        try {
            target.path("multiply/1s/3").request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (BadRequestException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }

    @Test
    public void testMultiplyRestServiceWithOverflowResult() {
        final WebTarget target = target("calculator");

        try {
            String firstParam = String.valueOf(Long.MAX_VALUE);
            String secondParam = "2";
            target.path("multiply/" + firstParam + "/" + secondParam).request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (InternalServerErrorException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }

    // Divide Service Test Cases

    @Test
    public void testDivideRestServiceSimple() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("divide/6/3").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(2.0, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testDivideRestServiceWithDecimal() {
        final WebTarget target = target("calculator");

        final CalculationResponse calculationResponse = target.path("divide/3.3/1.1").
                request(MediaType.APPLICATION_JSON).get(CalculationResponse.class);

        assertEquals(3.0, calculationResponse.getValue(), DELTA);
    }

    @Test
    public void testDivideRestServiceWithIllegalParameters() {
        final WebTarget target = target("calculator");

        try {
            target.path("divide/1s/3").request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (BadRequestException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }

    @Test
    public void testDivideRestServiceWithDivideByZero() {
        final WebTarget target = target("calculator");

        try {
            String firstParam = String.valueOf(Long.MAX_VALUE);
            String secondParam = "0";
            target.path("divide/" + firstParam + "/" + secondParam).request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (InternalServerErrorException exc) {
            // Success
        } catch (Exception exc) {
            fail();
        }
    }
}
