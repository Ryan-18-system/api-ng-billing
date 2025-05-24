package dev.ryan.nobrega.web;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.application.exception.dto.ValidationErrorDTO;
import dev.ryan.nobrega.application.service.AccountService;
import dev.ryan.nobrega.domain.model.dto.AccountDTO;
import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Set;

@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(
        name = "Account Resource",
        description = "operações com conta bancária")
public class AccountResource {
    private final AccountService service;
    private final Validator validator;

    @Inject
    public AccountResource(AccountService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @POST
    @Path("/transacao")
    @Operation(summary = "Processar uma transação através da conta, valor e método de pagamento")
    public Response processTransaction(BankTransactionDTO transaction) throws ApplicationServiceException {
        service.processBankTransaction(transaction);
        return Response.ok("Transação processada com sucesso!").build();
    }

    @POST
    @Path("/conta")
    @Operation(summary = "Criar uma conta")
    public Response createAccount(AccountDTO dto) throws ApplicationServiceException {
        Set<ConstraintViolation<AccountDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ValidationErrorDTO(violations)).build();
        }
        return Response.status(Response.Status.CREATED).entity(service.createBankAccount(dto)).build();
    }

    @GET
    @Path("/conta")
    @Operation(summary = "Buscar uma conta pelo número")
    public Response createAccount(  @Parameter(
            description = "Número da conta",
            example = "123456",
            required = true
    )@QueryParam("numero_conta") Integer accountNumber) throws ApplicationServiceException {
        return Response.status(Response.Status.OK).entity(service.findAccountByNumber(accountNumber)).build();
    }
}
