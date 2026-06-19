package com.ecoride.factory;

import com.ecoride.model.BilleteraVirtual;
import com.ecoride.model.ProcesadorPago;
import com.ecoride.model.TarjetaCredito;
import org.springframework.stereotype.Component;

@Component
public class ProcesadorPagoFactory {

    public ProcesadorPago obtenerProcesador(String metodoPago) {
        if (metodoPago == null) {
            throw new IllegalArgumentException("El metodo de pago no puede ser nulo.");
        }
        switch (metodoPago.toUpperCase()) {
            case "TARJETA":
                return new TarjetaCredito();
            case "BILLETERA":
                return new BilleteraVirtual();
            default:
                throw new IllegalArgumentException("Metodo de pago no reconocido: '" + metodoPago + "'. Use TARJETA o BILLETERA.");
        }
    }
}
