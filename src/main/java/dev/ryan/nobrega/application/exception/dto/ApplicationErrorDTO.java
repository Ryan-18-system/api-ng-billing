package dev.ryan.nobrega.application.exception.dto;

import java.io.Serial;
import java.io.Serializable;

public class ApplicationErrorDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7935011285907466113L;
    private   String message;

    private Integer statusCode;

    public ApplicationErrorDTO() {
    }

    public ApplicationErrorDTO(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
