package dev.ryan.nobrega.application.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.ryan.nobrega.application.exception.dto.ValidationErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<MismatchedInputException> {

    @Override
    public Response toResponse(MismatchedInputException exception) {
        String campo = null;
        String mensagem;

        if (exception.getPath() != null && !exception.getPath().isEmpty()) {
            campo = exception.getPath().get(0).getFieldName();
        }
        mensagem = "Erro ao processar campo '" + campo + "'. Verifique o formato do valor enviado.";

        if (exception instanceof InvalidFormatException invalidFormat) {
            mensagem = "Formato inválido para o campo '" + campo +
                    "'. Esperado: " + invalidFormat.getTargetType().getSimpleName() +
                    ", recebido: '" + invalidFormat.getValue() + "'";
        }

        ValidationErrorDTO dto = new ValidationErrorDTO(
                Response.Status.BAD_REQUEST.getStatusCode(),
                campo,
                mensagem
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(dto)
                .build();
    }
}