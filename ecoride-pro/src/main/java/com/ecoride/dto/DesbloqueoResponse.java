package com.ecoride.dto;

public class DesbloqueoResponse {

    private String patente;
    private String tipoVehiculo;
    private String estadoVehiculo;
    private String nombreUsuario;
    private String tipoUsuario;
    private double costoFinal;
    private String criterioTarifa;
    private String metodoPago;

    public DesbloqueoResponse(String patente, String tipoVehiculo, String estadoVehiculo,
                               String nombreUsuario, String tipoUsuario,
                               double costoFinal, String criterioTarifa, String metodoPago) {
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.estadoVehiculo = estadoVehiculo;
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipoUsuario;
        this.costoFinal = costoFinal;
        this.criterioTarifa = criterioTarifa;
        this.metodoPago = metodoPago;
    }

    public String getPatente() { return patente; }
    public String getTipoVehiculo() { return tipoVehiculo; }
    public String getEstadoVehiculo() { return estadoVehiculo; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getTipoUsuario() { return tipoUsuario; }
    public double getCostoFinal() { return costoFinal; }
    public String getCriterioTarifa() { return criterioTarifa; }
    public String getMetodoPago() { return metodoPago; }
}
