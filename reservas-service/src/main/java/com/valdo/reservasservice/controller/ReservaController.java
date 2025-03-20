package com.valdo.reservasservice.controller;

import com.valdo.reservasservice.model.Reserva;
import com.valdo.reservasservice.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el servicio de Reservas.
 * <p>
 * Expone endpoints para registrar nuevas reservas y consultar reservas por DNI.
 * </p>
 */
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    /**
     * Obtiene las reservas realizadas para un DNI específico.
     *
     * @param dni el DNI del cliente
     * @return lista de reservas correspondientes
     */
    @GetMapping("/{dni}")
    public List<Reserva> obtenerReservasPorDni(@PathVariable String dni) {
        return reservaService.obtenerReservasPorDni(dni);
    }

    /**
     * Registra una nueva reserva.
     * <p>
     * Se asume para 1 persona por defecto.
     * </p>
     *
     * @param reserva la información de la reserva a registrar
     * @return la reserva registrada
     */
    @PostMapping
    public Reserva realizarReserva(@RequestBody Reserva reserva) {
        return reservaService.realizarReserva(reserva, 1);
    }
}
