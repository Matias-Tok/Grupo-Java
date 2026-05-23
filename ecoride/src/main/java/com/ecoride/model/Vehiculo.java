package com.ecoride.model;
// Clase base abstracta que representa cualquier vehículo de la plataforma EcoRide
public abstract class Vehiculo {

    private String patente;
    private int porcentajeBateria; // 0 a 100
    private double tarifaBase;

    public Vehiculo(String patente, int porcentajeBateria, double tarifaBase) {
        this.patente = patente;
        this.porcentajeBateria = porcentajeBateria;
        this.tarifaBase = tarifaBase;
    }

    public String getPatente() {
        return patente;
    }

    public int getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setPorcentajeBateria(int porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    @Override
    public String toString() {
        return "Vehiculo{patente='" + patente + "', bateria=" + porcentajeBateria + "%, tarifa=$" + tarifaBase + "}";
    }
}
