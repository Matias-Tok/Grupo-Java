package com.ecoride.model;

// Procesador de pago mediante Tarjeta de Crédito
public class TarjetaCredito implements ProcesadorPago {

    @Override
    public void procesarPago(double monto) {
        System.out.println("Cobro exitoso de $" + String.format("%.2f", monto) + " realizado con Tarjeta de Crédito.");
    }
}
