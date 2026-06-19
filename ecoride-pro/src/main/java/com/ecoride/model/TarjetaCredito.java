package com.ecoride.model;

public class TarjetaCredito implements ProcesadorPago {

    @Override
    public void procesarPago(double monto) {
        System.out.println("Cobro exitoso de $" + String.format("%.2f", monto) + " realizado con Tarjeta de Credito.");
    }
}
