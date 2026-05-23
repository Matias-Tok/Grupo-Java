package com.ecoride.model;

// Clase base que representa a un usuario de la plataforma
public class Usuario {

    private String id;
    private String nombreCompleto;

    public Usuario(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    // Calcula el importe final a pagar según el tipo de usuario
    // En UsuarioRegular no hay descuento
    public double calcularImporte(double tarifaBase) {
        return tarifaBase;
    }

    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nombre='" + nombreCompleto + "'}";
    }
}
