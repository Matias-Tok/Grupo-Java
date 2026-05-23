package com.ecoride.service;

// Excepción lanzada cuando el nivel de batería del vehículo es menor al 15%
public class BateriaInsuficienteException extends RuntimeException {
    public BateriaInsuficienteException(String patente, int porcentaje) {
        super("Batería Insuficiente: El vehículo con patente '" + patente
                + "' tiene solo " + porcentaje + "% de batería. Se requiere mínimo 15%.");
    }
}
