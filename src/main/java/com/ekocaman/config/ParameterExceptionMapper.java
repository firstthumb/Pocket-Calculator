package com.ekocaman.config;

import com.ekocaman.model.ErrorResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ParameterExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger logger = Logger.getLogger(ParameterExceptionMapper.class.getName());

    @Override
    public Response toResponse(Throwable exception) {
        logger.log(Level.SEVERE, "Exception occurred on calculation : " + exception);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(Constants.ERROR_CODE_ILLEGAL_PARAMETER);
        errorResponse.setMessage("Illegal Parameter");

        return Response.status(Response.Status.BAD_REQUEST).
                entity(errorResponse).
                type(MediaType.APPLICATION_JSON).
                build();
    }
}
