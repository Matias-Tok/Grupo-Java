package com.ecoride.exception;

public class VehiculoNoEncontradoException extends RuntimeException {
    public VehiculoNoEncontradoException(String patente) {
        super("Vehiculo No Encontrado: No existe un vehiculo con patente '" + patente + "'.");
    }
}
