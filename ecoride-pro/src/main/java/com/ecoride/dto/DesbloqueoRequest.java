package com.ecoride.dto;

public class DesbloqueoRequest {

    private String idUsuario;
    private String patente;
    private String metodoPago;

    public DesbloqueoRequest() {}

    public String getIdUsuario() { return idUsuario; }
    public String getPatente() { return patente; }
    public String getMetodoPago() { return metodoPago; }

    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public void setPatente(String patente) { this.patente = patente; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}
