package com.ecoride.model;

// Procesador de pago mediante Billetera Virtual
public class BilleteraVirtual implements ProcesadorPago {

    @Override
    public void procesarPago(double monto) {
        System.out.println("Cobro exitoso de $" + String.format("%.2f", monto) + " realizado con Billetera Virtual.");
    }
}
