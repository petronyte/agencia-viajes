package com.valdo.reservasservice.service;

import com.valdo.reservasservice.model.Reserva;
import com.valdo.reservasservice.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * Servicio para gestionar la lógica de negocio de reservas.
 * <p>
 * Actualiza las plazas en el servicio de vuelos y registra la reserva en la base de datos.
 * </p>
 */
@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestTemplate restTemplate;

    // URL base del servicio de vuelos, resuelta mediante Eureka (LoadBalanced)
    private final String URL_VUELOS = "http://vuelos-service/vuelos";

    /**
     * Obtiene las reservas de un cliente basado en su DNI.
     *
     * @param dni el DNI del cliente
     * @return lista de reservas
     */
    public List<Reserva> obtenerReservasPorDni(String dni) {
        return reservaRepository.findByDni(dni);
    }

    /**
     * Realiza una nueva reserva.
     * <p>
     * Actualiza la cantidad de asientos disponibles en el servicio de vuelos y guarda la reserva.
     * </p>
     *
     * @param reserva      la reserva a realizar
     * @param totalPersonas número de personas para la reserva
     * @return la reserva registrada
     */
    public Reserva realizarReserva(Reserva reserva, int totalPersonas) {
        // Llama al servicio de vuelos para actualizar las plazas disponibles
        restTemplate.put(URL_VUELOS + "/{id}/actualizarPlazas/{cantidad}",
                null, reserva.getVueloId(), totalPersonas);
        // Guarda la reserva en la base de datos
        return reservaRepository.save(reserva);
    }
}
