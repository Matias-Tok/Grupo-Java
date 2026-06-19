package com.ecoride.dto;

public class FinalizarRequest {

    private String patente;
    private int minutosViaje;

    public FinalizarRequest() {}

    public String getPatente() { return patente; }
    public int getMinutosViaje() { return minutosViaje; }

    public void setPatente(String patente) { this.patente = patente; }
    public void setMinutosViaje(int minutosViaje) { this.minutosViaje = minutosViaje; }
}
