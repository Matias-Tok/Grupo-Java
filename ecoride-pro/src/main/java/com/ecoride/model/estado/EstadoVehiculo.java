package com.ecoride.model.estado;

import com.ecoride.model.Vehiculo;

public interface EstadoVehiculo {
    void iniciarViaje(Vehiculo vehiculo);
    void finalizarViaje(Vehiculo vehiculo);
    void enviarReparacion(Vehiculo vehiculo);
    String getNombre();
    boolean permiteAlquiler();
}
