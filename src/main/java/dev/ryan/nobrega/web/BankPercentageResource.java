package dev.ryan.nobrega.web;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.application.exception.dto.ValidationErrorDTO;
import dev.ryan.nobrega.application.service.BankPercentageService;
import dev.ryan.nobrega.domain.model.dto.BankPercentageDTO;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Set;

@Path("/bank-percentage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(
        name = "Bank Percentage Resource",
        description = "operações com  porcentagens bancárias")
public class BankPercentageResource {
    private final BankPercentageService service;
    private final Validator validator;

    @Inject
    public BankPercentageResource(BankPercentageService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @POST
    @Operation(summary = "Processar o cadastro de uma porcentagem")
    public Response createPercentage(BankPercentageDTO dto) throws ApplicationServiceException {
        Set<ConstraintViolation<BankPercentageDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ValidationErrorDTO(violations)).build();
        }
        return Response.status(Response.Status.CREATED).entity(service.createEntity(dto)).build();
    }


}
