package com.ecoride.model.tarifa;

public class TarifaTemporalClimatico implements CriterioTarifa {

    private static final double RECARGO_CLIMATICO = 150.0;

    @Override
    public double calcularCosto(double tarifaBase, int minutosViaje) {
        return (tarifaBase * minutosViaje) + RECARGO_CLIMATICO;
    }

    @Override
    public String getNombre() {
        return "TEMPORAL_CLIMATICO";
    }
}
