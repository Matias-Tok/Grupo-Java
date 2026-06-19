package com.ecoride.service;

import com.ecoride.dto.DesbloqueoResponse;
import com.ecoride.dto.FinalizarResponse;
import com.ecoride.dto.VehiculoResponse;
import com.ecoride.exception.BateriaInsuficienteException;
import com.ecoride.exception.UsuarioNoEncontradoException;
import com.ecoride.exception.VehiculoNoEncontradoException;
import com.ecoride.factory.ProcesadorPagoFactory;
import com.ecoride.model.*;
import com.ecoride.model.tarifa.CriterioTarifa;
import com.ecoride.model.tarifa.TarifaEstandar;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AlquilerService {

    private final ProcesadorPagoFactory procesadorPagoFactory;

    private Map<String, Vehiculo> vehiculosPorPatente;
    private List<Usuario> usuarios;
    private CriterioTarifa criterioTarifaActivo;

    public AlquilerService(ProcesadorPagoFactory procesadorPagoFactory) {
        this.procesadorPagoFactory = procesadorPagoFactory;
    }

    @PostConstruct
    private void inicializarDatos() {
        criterioTarifaActivo = new TarifaEstandar();

        vehiculosPorPatente = new HashMap<>();
        agregarVehiculo(new Monopatin("ABC123", 80, 500.0, true));
        agregarVehiculo(new Monopatin("DEF456", 10, 450.0, false));
        agregarVehiculo(new BicicletaElectrica("GHI789", 95, 600.0, 1500));
        agregarVehiculo(new BicicletaElectrica("JKL012", 60, 550.0, 2000));
        agregarVehiculo(new Monopatin("MNO345", 25, 480.0, true));

        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("U001", "Juan Perez"));
        usuarios.add(new Usuario("U002", "Maria Gonzalez"));
        usuarios.add(new UsuarioPremium("U003", "Carlos Lopez", 15.0));
        usuarios.add(new UsuarioPremium("U004", "Ana Martinez", 10.0));
    }

    private void agregarVehiculo(Vehiculo vehiculo) {
        vehiculosPorPatente.put(vehiculo.getPatente().toUpperCase(), vehiculo);
    }

    public void cambiarCriterioTarifa(CriterioTarifa nuevoCriterio) {
        this.criterioTarifaActivo = nuevoCriterio;
    }

    public String getCriterioTarifaActivo() {
        return criterioTarifaActivo.getNombre();
    }

    public DesbloqueoResponse desbloquearVehiculo(String idUsuario, String patente, String metodoPago) {
        Vehiculo vehiculo = vehiculosPorPatente.get(patente.toUpperCase());
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException(patente);
        }

        if (!vehiculo.permiteAlquiler()) {
            throw new IllegalStateException("El vehiculo '" + patente + "' no esta disponible. Estado actual: " + vehiculo.getNombreEstado());
        }

        if (vehiculo.getPorcentajeBateria() < 15) {
            throw new BateriaInsuficienteException(patente, vehiculo.getPorcentajeBateria());
        }

        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException(idUsuario);
        }

        double costoInicial = criterioTarifaActivo.calcularCosto(vehiculo.getTarifaBase(), 1);
        double costoFinal = usuario.calcularImporte(costoInicial);

        ProcesadorPago procesador = procesadorPagoFactory.obtenerProcesador(metodoPago);
        procesador.procesarPago(costoFinal);

        vehiculo.iniciarViaje();

        return new DesbloqueoResponse(
                vehiculo.getPatente(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getNombreEstado(),
                usuario.getNombreCompleto(),
                usuario.getTipoUsuario(),
                costoFinal,
                criterioTarifaActivo.getNombre(),
                metodoPago.toUpperCase()
        );
    }

    public FinalizarResponse finalizarViaje(String patente, int minutosViaje) {
        Vehiculo vehiculo = vehiculosPorPatente.get(patente.toUpperCase());
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException(patente);
        }

        double costoFinal = criterioTarifaActivo.calcularCosto(vehiculo.getTarifaBase(), minutosViaje);

        vehiculo.finalizarViaje();

        return new FinalizarResponse(
                vehiculo.getPatente(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getNombreEstado(),
                minutosViaje,
                costoFinal,
                criterioTarifaActivo.getNombre()
        );
    }

    public List<String> deduplicarAlertasGPS(List<String> alertas) {
        Set<String> vistas = new HashSet<>();
        List<String> unicas = new ArrayList<>();
        for (String alerta : alertas) {
            if (vistas.add(alerta)) {
                unicas.add(alerta);
            }
        }
        return unicas;
    }

    public List<VehiculoResponse> listarPorPrioridadCarga() {
        List<Vehiculo> lista = new ArrayList<>(vehiculosPorPatente.values());
        Collections.sort(lista);
        List<VehiculoResponse> resultado = new ArrayList<>();
        for (Vehiculo v : lista) {
            resultado.add(new VehiculoResponse(v.getPatente(), v.getTipoVehiculo(), v.getPorcentajeBateria(), v.getTarifaBase(), v.getNombreEstado()));
        }
        return resultado;
    }

    public List<VehiculoResponse> listarPorTarifaDescendente() {
        List<Vehiculo> lista = new ArrayList<>(vehiculosPorPatente.values());
        Collections.sort(lista, new ComparadorTarifaDescendente());
        List<VehiculoResponse> resultado = new ArrayList<>();
        for (Vehiculo v : lista) {
            resultado.add(new VehiculoResponse(v.getPatente(), v.getTipoVehiculo(), v.getPorcentajeBateria(), v.getTarifaBase(), v.getNombreEstado()));
        }
        return resultado;
    }

    private Usuario buscarUsuarioPorId(String idUsuario) {
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(idUsuario)) {
                return u;
            }
        }
        return null;
    }
}
