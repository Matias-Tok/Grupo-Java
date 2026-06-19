package com.ecoride.controller;

import com.ecoride.dto.VehiculoResponse;
import com.ecoride.service.AlquilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final AlquilerService alquilerService;

    public VehiculoController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/prioridad-carga")
    public ResponseEntity<List<VehiculoResponse>> listarPorPrioridadCarga() {
        return ResponseEntity.ok(alquilerService.listarPorPrioridadCarga());
    }

    @GetMapping("/tarifa-descendente")
    public ResponseEntity<List<VehiculoResponse>> listarPorTarifaDescendente() {
        return ResponseEntity.ok(alquilerService.listarPorTarifaDescendente());
    }
}
