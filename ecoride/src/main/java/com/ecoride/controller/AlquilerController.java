package com.ecoride.controller;

import com.ecoride.service.AlquilerService;
import com.ecoride.service.BateriaInsuficienteException;
import com.ecoride.service.VehiculoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST de EcoRide.
 * Expone el endpoint GET /api/alquileres/desbloquear
 * que recibe un JSON con idUsuario, patente y metodoPago.
 */
@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    // Inyección de dependencias por constructor (buena práctica Spring)
    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    /**
     * Endpoint principal de desbloqueo de vehículo.
     *
     * Método: GET
     * Ruta:   /api/alquileres/desbloquear
     * Cuerpo: JSON con { idUsuario, patente, metodoPago }
     *
     * Respuestas posibles:
     * - 200 OK:          Desbloqueo exitoso con detalle del cobro
     * - 404 Not Found:   Vehículo no encontrado en la estación
     * - 400 Bad Request: Batería insuficiente o método de pago inválido
     */
    @GetMapping("/desbloquear")
    public ResponseEntity<Map<String, String>> desbloquear(@RequestBody DesbloqueoRequest request) {
        Map<String, String> respuesta = new HashMap<>();

        try {
            String resultado = alquilerService.desbloquearVehiculo(
                    request.getIdUsuario(),
                    request.getPatente(),
                    request.getMetodoPago()
            );

            respuesta.put("estado", "EXITOSO");
            respuesta.put("mensaje", resultado);
            return ResponseEntity.ok(respuesta);

        } catch (VehiculoNoEncontradoException e) {
            // Alarma: Vehículo No Encontrado
            respuesta.put("estado", "ERROR");
            respuesta.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);

        } catch (BateriaInsuficienteException e) {
            // Alarma: Batería Insuficiente
            respuesta.put("estado", "ERROR");
            respuesta.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);

        } catch (IllegalArgumentException e) {
            // Método de pago inválido u otro error de validación
            respuesta.put("estado", "ERROR");
            respuesta.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
    }
}
