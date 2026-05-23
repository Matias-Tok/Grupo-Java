package com.ecoride.factory;

import com.ecoride.model.BilleteraVirtual;
import com.ecoride.model.ProcesadorPago;
import com.ecoride.model.TarjetaCredito;

/**
 * Fábrica de procesadores de pago.
 * Desacopla la creación del procesador del sistema principal.
 * Recibe el nombre del medio de pago y devuelve el procesador correspondiente.
 */
public class ProcesadorPagoFactory {

    public static ProcesadorPago obtenerProcesador(String metodoPago) {
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo.");
        }

        switch (metodoPago.toUpperCase()) {
            case "TARJETA":
                return new TarjetaCredito();
            case "BILLETERA":
                return new BilleteraVirtual();
            default:
                throw new IllegalArgumentException("Método de pago no reconocido: " + metodoPago
                        + ". Use 'TARJETA' o 'BILLETERA'.");
        }
    }
}
