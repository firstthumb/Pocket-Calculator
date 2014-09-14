package com.ekocaman.config;

import com.ekocaman.model.ErrorResponse;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ArithmeticExceptionMapper implements ExceptionMapper<ArithmeticException> {
    private static final Logger logger = Logger.getLogger(ArithmeticExceptionMapper.class.getName());

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ArithmeticException exception) {
        logger.log(Level.SEVERE, "Exception occurred on calculation : " + exception);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(Constants.ERROR_CODE_OVERFLOW);
        errorResponse.setMessage("Arithmetic Exception occurred");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(errorResponse).
                type(MediaType.APPLICATION_JSON).
                build();
    }
}
