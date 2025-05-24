package dev.ryan.nobrega.application.exception.handler;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.application.exception.dto.ApplicationErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApplicationServiceExceptionMapper implements ExceptionMapper<ApplicationServiceException> {
    @Override
    public Response toResponse(ApplicationServiceException e) {
        int statusCode = e.getStatusCode() != null ? e.getStatusCode() : Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        return Response.status(statusCode)
                .entity(new ApplicationErrorDTO(e.getMessage(), statusCode))
                .build();
    }
}
