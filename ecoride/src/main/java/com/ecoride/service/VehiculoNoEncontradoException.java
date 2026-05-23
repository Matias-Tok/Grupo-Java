package com.ecoride.service;

// Excepción lanzada cuando un vehículo no es encontrado en la estación
public class VehiculoNoEncontradoException extends RuntimeException {
    public VehiculoNoEncontradoException(String patente) {
        super("Vehículo No Encontrado: No existe un vehículo con patente '" + patente + "' en la estación.");
    }
}
