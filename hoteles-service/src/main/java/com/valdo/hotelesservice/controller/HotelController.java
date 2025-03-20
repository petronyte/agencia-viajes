package com.valdo.hotelesservice.controller;

import com.valdo.hotelesservice.model.Hotel;
import com.valdo.hotelesservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el servicio de hoteles.
 * <p>
 * Expone endpoints para obtener la lista de hoteles disponibles.
 * </p>
 */
@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * Obtiene la lista de hoteles que se encuentran disponibles.
     *
     * @return lista de hoteles disponibles
     */
    @GetMapping("/disponibles")
    public List<Hotel> obtenerHotelesDisponibles() {
        return hotelService.obtenerHotelesDisponibles();
    }
}
