package com.ecoride.model;

import java.util.ArrayList;
import java.util.List;

// Representa una estación de anclaje donde se guardan los vehículos disponibles
public class EstacionAnclaje {

    private String nombre;
    private List<Vehiculo> vehiculosDisponibles;

    public EstacionAnclaje(String nombre) {
        this.nombre = nombre;
        this.vehiculosDisponibles = new ArrayList<>();
    }

    // Agrega un vehículo a la estación
    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculosDisponibles.add(vehiculo);
    }

    /**
     * Busca un vehículo por patente inspeccionando la colección elemento por elemento.
     * Retorna el vehículo si lo encuentra, o null si no existe en esta estación.
     * CONSIGNA: búsqueda manual con bucle (no streams ni métodos directos de colecciones).
     */
    public Vehiculo buscarVehiculoPorPatente(String patente) {
        for (Vehiculo vehiculo : vehiculosDisponibles) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                return vehiculo;
            }
        }
        return null; // No encontrado
    }

    public String getNombre() {
        return nombre;
    }

    public List<Vehiculo> getVehiculosDisponibles() {
        return vehiculosDisponibles;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstacionAnclaje{nombre='" + nombre + "', vehiculos=" + vehiculosDisponibles.size() + "}";
    }
}
