package com.ecoride.model;

public class BicicletaElectrica extends Vehiculo {

    private int capacidadCanasto;

    public BicicletaElectrica(String patente, int porcentajeBateria, double tarifaBase, int capacidadCanasto) {
        super(patente, porcentajeBateria, tarifaBase);
        this.capacidadCanasto = capacidadCanasto;
    }

    @Override
    public String getTipoVehiculo() {
        return "Bicicleta Electrica";
    }

    public int getCapacidadCanasto() { return capacidadCanasto; }
    public void setCapacidadCanasto(int capacidadCanasto) { this.capacidadCanasto = capacidadCanasto; }
}
