package com.ecoride.model.estado;

import com.ecoride.model.Vehiculo;

public class EstadoEnEspera implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnViaje());
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehiculo no esta en viaje.");
    }

    @Override
    public void enviarReparacion(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnReparacion());
    }

    @Override
    public String getNombre() {
        return "EN_ESPERA";
    }

    @Override
    public boolean permiteAlquiler() {
        return true;
    }
}
