package com.ecoride.service;

import com.ecoride.factory.ProcesadorPagoFactory;
import com.ecoride.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio principal de EcoRide.
 * Gestiona la lógica de negocio: usuarios, estaciones, vehículos y desbloqueo.
 * Los datos se mantienen EN MEMORIA (sin base de datos).
 */
@Service
public class AlquilerService {

    private List<Usuario> usuarios;
    private EstacionAnclaje estacion;

    // Constructor: inicializa datos de prueba en memoria
    public AlquilerService() {
        inicializarDatos();
    }

    /**
     * Carga datos de prueba en memoria para simular el sistema.
     */
    private void inicializarDatos() {
        // Usuarios
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("U001", "Juan Pérez"));
        usuarios.add(new Usuario("U002", "María González"));
        usuarios.add(new UsuarioPremium("U003", "Carlos López", 15.0));  // 15% descuento
        usuarios.add(new UsuarioPremium("U004", "Ana Martínez", 10.0));  // 10% descuento

        // Estación única con vehículos disponibles
        estacion = new EstacionAnclaje("Estación Central");
        estacion.agregarVehiculo(new Monopatin("ABC123", 80, 500.0, true));
        estacion.agregarVehiculo(new Monopatin("DEF456", 10, 450.0, false)); // batería baja para probar alarma
        estacion.agregarVehiculo(new BicicletaElectrica("GHI789", 95, 600.0, 1500));
        estacion.agregarVehiculo(new BicicletaElectrica("JKL012", 60, 550.0, 2000));
    }

    /**
     * Busca un usuario por su ID recorriendo la lista elemento por elemento (bucle tradicional).
     */
    public Usuario buscarUsuarioPorId(String idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equalsIgnoreCase(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Proceso principal de desbloqueo de un vehículo.
     *
     * Secuencia:
     * 1. Localizar el vehículo en la estación por patente
     * 2. Validar nivel de batería >= 15%
     * 3. Calcular importe (aplicando descuento si es Premium)
     * 4. Obtener procesador de pago mediante Factory
     * 5. Efectuar el cobro
     * 6. Retornar respuesta exitosa
     *
     * @param idUsuario   ID del usuario que solicita el viaje
     * @param patente     Patente del vehículo a desbloquear
     * @param metodoPago  "TARJETA" o "BILLETERA"
     * @return Mensaje de éxito con detalles del desbloqueo
     */
    public String desbloquearVehiculo(String idUsuario, String patente, String metodoPago) {

        // 1. Localizar el vehículo en la estación por patente
        Vehiculo vehiculo = estacion.buscarVehiculoPorPatente(patente);
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException(patente);
        }

        // 2. Validar que la batería sea apta (>= 15%)
        if (vehiculo.getPorcentajeBateria() < 15) {
            throw new BateriaInsuficienteException(patente, vehiculo.getPorcentajeBateria());
        }

        // 3. Calcular el importe según el tipo de usuario (Premium aplica descuento)
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        double importeFinal;
        String tipoUsuario;

        if (usuario != null) {
            importeFinal = usuario.calcularImporte(vehiculo.getTarifaBase());
            tipoUsuario = (usuario instanceof UsuarioPremium) ? "Premium" : "Regular";
        } else {
            // Si el usuario no existe, se cobra tarifa completa igual
            importeFinal = vehiculo.getTarifaBase();
            tipoUsuario = "Desconocido";
        }

        // 4. Obtener el procesador de pago via Factory (desacoplamiento)
        ProcesadorPago procesador = ProcesadorPagoFactory.obtenerProcesador(metodoPago);

        // 5. Efectuar el cobro (imprime en consola del servidor)
        procesador.procesarPago(importeFinal);

        // 6. Retornar respuesta exitosa con detalle
        return String.format(
                "Desbloqueo exitoso. Vehículo: %s | Usuario: %s (%s) | Monto cobrado: $%.2f | Método: %s",
                patente,
                (usuario != null ? usuario.getNombreCompleto() : idUsuario),
                tipoUsuario,
                importeFinal,
                metodoPago.toUpperCase()
        );
    }
}
