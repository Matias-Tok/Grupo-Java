package com.ecoride.model.tarifa;

public class TarifaHoraPico implements CriterioTarifa {

    @Override
    public double calcularCosto(double tarifaBase, int minutosViaje) {
        double costoBase = tarifaBase * minutosViaje;
        double recargo = costoBase * 0.40;
        return costoBase + recargo;
    }

    @Override
    public String getNombre() {
        return "HORA_PICO";
    }
}
