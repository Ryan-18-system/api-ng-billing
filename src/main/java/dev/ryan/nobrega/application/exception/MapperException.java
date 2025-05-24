package dev.ryan.nobrega.application.exception;

public class MapperException extends RuntimeException {
    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
