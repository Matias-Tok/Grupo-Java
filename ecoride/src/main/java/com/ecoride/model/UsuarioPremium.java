package com.ecoride.model;

// Subclase de Usuario con descuento fijo sobre la tarifa
public class UsuarioPremium extends Usuario {

    // Porcentaje de descuento, ej: 10.0 = 10%, 15.0 = 15%
    private double porcentajeDescuento;

    public UsuarioPremium(String id, String nombreCompleto, double porcentajeDescuento) {
        super(id, nombreCompleto);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Aplica el descuento exclusivo Premium sobre la tarifa base
    @Override
    public double calcularImporte(double tarifaBase) {
        double descuento = tarifaBase * (porcentajeDescuento / 100.0);
        return tarifaBase - descuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "UsuarioPremium{id='" + getId() + "', nombre='" + getNombreCompleto()
                + "', descuento=" + porcentajeDescuento + "%}";
    }
}
