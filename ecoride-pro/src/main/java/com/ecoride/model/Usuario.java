package com.ecoride.model;

public class Usuario {

    private String id;
    private String nombreCompleto;

    public Usuario(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public double calcularImporte(double tarifaBase) {
        return tarifaBase;
    }

    public String getTipoUsuario() {
        return "Regular";
    }

    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setId(String id) { this.id = id; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
}
