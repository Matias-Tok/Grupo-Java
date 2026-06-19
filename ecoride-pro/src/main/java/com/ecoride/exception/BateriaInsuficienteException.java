package com.ecoride.exception;

public class BateriaInsuficienteException extends RuntimeException {
    public BateriaInsuficienteException(String patente, int porcentaje) {
        super("Bateria Insuficiente: El vehiculo '" + patente + "' tiene " + porcentaje + "% de bateria. Minimo requerido: 15%.");
    }
}
