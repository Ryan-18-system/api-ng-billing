package dev.ryan.nobrega.application.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorDTO {
    private int status;
    private String campo;
    private String mensagem;

    public ValidationErrorDTO() {
    }

    public ValidationErrorDTO(int status, String campo, String mensagem) {
        this.status = status;
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ValidationErrorDTO(Set<? extends ConstraintViolation<?>> violations) {
        this.status = 400;
        this.mensagem = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
