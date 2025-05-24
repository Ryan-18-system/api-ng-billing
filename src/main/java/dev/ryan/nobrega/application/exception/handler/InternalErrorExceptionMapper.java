package dev.ryan.nobrega.application.exception.handler;

import dev.ryan.nobrega.application.exception.dto.ApplicationErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InternalErrorExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ApplicationErrorDTO(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                .build();
    }
}