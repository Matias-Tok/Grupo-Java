package com.ecoride.model;

// Subclase de Vehiculo que representa una Bicicleta Eléctrica
public class BicicletaElectrica extends Vehiculo {

    // Capacidad del canasto de carga frontal en centímetros cúbicos
    private int capacidadCanasto;

    public BicicletaElectrica(String patente, int porcentajeBateria, double tarifaBase, int capacidadCanasto) {
        super(patente, porcentajeBateria, tarifaBase);
        this.capacidadCanasto = capacidadCanasto;
    }

    public int getCapacidadCanasto() {
        return capacidadCanasto;
    }

    public void setCapacidadCanasto(int capacidadCanasto) {
        this.capacidadCanasto = capacidadCanasto;
    }

    @Override
    public String toString() {
        return "BicicletaElectrica{patente='" + getPatente() + "', bateria=" + getPorcentajeBateria()
                + "%, tarifa=$" + getTarifaBase()
                + ", canasto=" + capacidadCanasto + "cm³}";
    }
}
