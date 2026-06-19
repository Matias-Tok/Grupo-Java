package com.ecoride.model.tarifa;

public class TarifaEstandar implements CriterioTarifa {

    @Override
    public double calcularCosto(double tarifaBase, int minutosViaje) {
        return tarifaBase * minutosViaje;
    }

    @Override
    public String getNombre() {
        return "ESTANDAR";
    }
}
