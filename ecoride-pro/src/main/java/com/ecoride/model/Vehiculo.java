package com.ecoride.model;

import com.ecoride.model.estado.EstadoEnEspera;
import com.ecoride.model.estado.EstadoVehiculo;

public abstract class Vehiculo implements Comparable<Vehiculo> {

    private String patente;
    private int porcentajeBateria;
    private double tarifaBase;
    private EstadoVehiculo estado;

    public Vehiculo(String patente, int porcentajeBateria, double tarifaBase) {
        this.patente = patente;
        this.porcentajeBateria = porcentajeBateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EstadoEnEspera();
    }

    public abstract String getTipoVehiculo();

    public void iniciarViaje() {
        estado.iniciarViaje(this);
    }

    public void finalizarViaje() {
        estado.finalizarViaje(this);
    }

    public void enviarReparacion() {
        estado.enviarReparacion(this);
    }

    public boolean permiteAlquiler() {
        return estado.permiteAlquiler();
    }

    public String getNombreEstado() {
        return estado.getNombre();
    }

    @Override
    public int compareTo(Vehiculo otro) {
        return Integer.compare(this.porcentajeBateria, otro.porcentajeBateria);
    }

    public String getPatente() { return patente; }
    public int getPorcentajeBateria() { return porcentajeBateria; }
    public double getTarifaBase() { return tarifaBase; }
    public EstadoVehiculo getEstado() { return estado; }

    public void setPatente(String patente) { this.patente = patente; }
    public void setPorcentajeBateria(int porcentajeBateria) { this.porcentajeBateria = porcentajeBateria; }
    public void setTarifaBase(double tarifaBase) { this.tarifaBase = tarifaBase; }
    public void setEstado(EstadoVehiculo estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Vehiculo{patente='" + patente + "', bateria=" + porcentajeBateria + "%, tarifa=$" + tarifaBase + ", estado=" + estado.getNombre() + "}";
    }
}
