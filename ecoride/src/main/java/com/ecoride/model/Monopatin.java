package com.ecoride.model;

// Subclase de Vehiculo que representa un Monopatín
public class Monopatin extends Vehiculo {

    // true si tiene sistema de amortiguación reforzada, false si no
    private boolean tieneAmortiguacionReforzada;

    public Monopatin(String patente, int porcentajeBateria, double tarifaBase, boolean tieneAmortiguacionReforzada) {
        super(patente, porcentajeBateria, tarifaBase);
        this.tieneAmortiguacionReforzada = tieneAmortiguacionReforzada;
    }

    public boolean isTieneAmortiguacionReforzada() {
        return tieneAmortiguacionReforzada;
    }

    public void setTieneAmortiguacionReforzada(boolean tieneAmortiguacionReforzada) {
        this.tieneAmortiguacionReforzada = tieneAmortiguacionReforzada;
    }

    @Override
    public String toString() {
        return "Monopatin{patente='" + getPatente() + "', bateria=" + getPorcentajeBateria()
                + "%, tarifa=$" + getTarifaBase()
                + ", amortiguacionReforzada=" + tieneAmortiguacionReforzada + "}";
    }
}
