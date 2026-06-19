package com.ecoride.model.estado;

import com.ecoride.model.Vehiculo;

public class EstadoEnViaje implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehiculo ya esta en viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnEspera());
    }

    @Override
    public void enviarReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("No se puede enviar a reparacion un vehiculo en viaje.");
    }

    @Override
    public String getNombre() {
        return "EN_VIAJE";
    }

    @Override
    public boolean permiteAlquiler() {
        return false;
    }
}
