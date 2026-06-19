package com.ecoride.model.tarifa;

public interface CriterioTarifa {
    double calcularCosto(double tarifaBase, int minutosViaje);
    String getNombre();
}
