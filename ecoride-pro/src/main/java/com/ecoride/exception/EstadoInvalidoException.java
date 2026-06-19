package com.ecoride.exception;

public class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException(String mensaje) {
        super("Estado Invalido: " + mensaje);
    }
}
