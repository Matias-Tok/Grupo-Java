package com.ecoride.model;

public class UsuarioPremium extends Usuario {

    private double porcentajeDescuento;

    public UsuarioPremium(String id, String nombreCompleto, double porcentajeDescuento) {
        super(id, nombreCompleto);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularImporte(double tarifaBase) {
        double descuento = tarifaBase * (porcentajeDescuento / 100.0);
        return tarifaBase - descuento;
    }

    @Override
    public String getTipoUsuario() {
        return "Premium";
    }

    public double getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(double v) { this.porcentajeDescuento = v; }
}
