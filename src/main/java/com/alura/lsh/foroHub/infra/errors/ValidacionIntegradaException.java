package com.alura.lsh.foroHub.infra.errors;

public class ValidacionIntegradaException extends RuntimeException {
    public ValidacionIntegradaException(String message){
        super(message);
    }
}
