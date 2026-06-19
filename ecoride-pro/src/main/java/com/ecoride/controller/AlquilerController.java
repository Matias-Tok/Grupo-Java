package com.ecoride.controller;

import com.ecoride.dto.DesbloqueoRequest;
import com.ecoride.dto.DesbloqueoResponse;
import com.ecoride.dto.FinalizarRequest;
import com.ecoride.dto.FinalizarResponse;
import com.ecoride.service.AlquilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/desbloquear")
    public ResponseEntity<DesbloqueoResponse> desbloquear(@RequestBody DesbloqueoRequest request) {
        DesbloqueoResponse response = alquilerService.desbloquearVehiculo(
                request.getIdUsuario(),
                request.getPatente(),
                request.getMetodoPago()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/finalizar")
    public ResponseEntity<FinalizarResponse> finalizar(@RequestBody FinalizarRequest request) {
        FinalizarResponse response = alquilerService.finalizarViaje(
                request.getPatente(),
                request.getMinutosViaje()
        );
        return ResponseEntity.ok(response);
    }
}
