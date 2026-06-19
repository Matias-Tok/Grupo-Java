package com.ecoride.model.estado;

import com.ecoride.model.Vehiculo;

public class EstadoEnReparacion implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehiculo esta en reparacion y no puede iniciar un viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehiculo no esta en viaje.");
    }

    @Override
    public void enviarReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehiculo ya esta en reparacion.");
    }

    @Override
    public String getNombre() {
        return "EN_REPARACION";
    }

    @Override
    public boolean permiteAlquiler() {
        return false;
    }
}
