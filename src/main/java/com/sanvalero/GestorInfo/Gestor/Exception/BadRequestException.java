package com.sanvalero.GestorInfo.Gestor.Exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}