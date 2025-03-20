package com.valdo.vuelosservice.service;

import com.valdo.vuelosservice.model.Vuelo;
import com.valdo.vuelosservice.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestionar la lógica de negocio de vuelos.
 */
@Service
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    /**
     * Obtiene la lista de vuelos con al menos la cantidad especificada de asientos disponibles.
     *
     * @param asientosMinimos cantidad mínima de asientos
     * @return lista de vuelos
     */
    public List<Vuelo> obtenerVuelosDisponibles(int asientosMinimos) {
        return vueloRepository.findByAsientosDisponiblesGreaterThan(asientosMinimos);
    }

    /**
     * Actualiza las plazas disponibles de un vuelo, restando la cantidad indicada.
     *
     * @param vueloId  identificador del vuelo
     * @param cantidad cantidad de asientos a restar
     */
    public void actualizarPlazas(Long vueloId, int cantidad) {
        Vuelo vuelo = vueloRepository.findById(vueloId)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        int nuevosAsientos = vuelo.getAsientosDisponibles() - cantidad;
        if (nuevosAsientos < 0) {
            throw new RuntimeException("No hay suficientes asientos disponibles");
        }
        vuelo.setAsientosDisponibles(nuevosAsientos);
        vueloRepository.save(vuelo);
    }
}
