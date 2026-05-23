package com.ecoride.model;

// Interfaz que deben implementar todos los procesadores de pago
public interface ProcesadorPago {
    void procesarPago(double monto);
}
