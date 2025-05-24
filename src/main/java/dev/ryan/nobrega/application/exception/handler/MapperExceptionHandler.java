package dev.ryan.nobrega.application.exception.handler;

import dev.ryan.nobrega.application.exception.MapperException;
import dev.ryan.nobrega.application.exception.dto.ApplicationErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MapperExceptionHandler implements ExceptionMapper<MapperException> {

    @Override
    public Response toResponse(MapperException ex) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApplicationErrorDTO(ex.getMessage(),Response.Status.BAD_REQUEST.getStatusCode()))
                .build();
    }
}