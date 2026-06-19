package com.ecoride.dto;

public class FinalizarResponse {

    private String patente;
    private String tipoVehiculo;
    private String estadoFinal;
    private int minutosViaje;
    private double costoFinal;
    private String criterioTarifa;

    public FinalizarResponse(String patente, String tipoVehiculo, String estadoFinal,
                              int minutosViaje, double costoFinal, String criterioTarifa) {
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.estadoFinal = estadoFinal;
        this.minutosViaje = minutosViaje;
        this.costoFinal = costoFinal;
        this.criterioTarifa = criterioTarifa;
    }

    public String getPatente() { return patente; }
    public String getTipoVehiculo() { return tipoVehiculo; }
    public String getEstadoFinal() { return estadoFinal; }
    public int getMinutosViaje() { return minutosViaje; }
    public double getCostoFinal() { return costoFinal; }
    public String getCriterioTarifa() { return criterioTarifa; }
}
