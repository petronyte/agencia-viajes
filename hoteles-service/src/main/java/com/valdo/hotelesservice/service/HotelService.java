package com.valdo.hotelesservice.service;

import com.valdo.hotelesservice.model.Hotel;
import com.valdo.hotelesservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestionar la lógica de negocio relacionada con hoteles.
 */
@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    /**
     * Obtiene la lista de hoteles disponibles.
     *
     * @return lista de hoteles disponibles
     */
    public List<Hotel> obtenerHotelesDisponibles() {
        return hotelRepository.findByDisponibleTrue();
    }
}
