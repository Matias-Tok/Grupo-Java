package com.ecoride.model;

public class Monopatin extends Vehiculo {

    private boolean tieneAmortiguacionReforzada;

    public Monopatin(String patente, int porcentajeBateria, double tarifaBase, boolean tieneAmortiguacionReforzada) {
        super(patente, porcentajeBateria, tarifaBase);
        this.tieneAmortiguacionReforzada = tieneAmortiguacionReforzada;
    }

    @Override
    public String getTipoVehiculo() {
        return "Monopatin";
    }

    public boolean isTieneAmortiguacionReforzada() { return tieneAmortiguacionReforzada; }
    public void setTieneAmortiguacionReforzada(boolean v) { this.tieneAmortiguacionReforzada = v; }
}
