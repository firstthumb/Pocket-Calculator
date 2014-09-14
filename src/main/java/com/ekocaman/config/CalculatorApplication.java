package com.ekocaman.config;

import com.ekocaman.rest.CalculatorResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;


public class CalculatorApplication extends ResourceConfig {

    public CalculatorApplication() {
        register(RequestContextFilter.class);
        register(CalculatorResource.class);
        register(ParameterExceptionMapper.class);
        register(ArithmeticExceptionMapper.class);
    }
}
