package com.valdo.vuelosservice.controller;

import com.valdo.vuelosservice.model.Vuelo;
import com.valdo.vuelosservice.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el servicio de Vuelos.
 * <p>
 * Expone endpoints para obtener vuelos disponibles y actualizar las plazas.
 * </p>
 */
@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    /**
     * Obtiene la lista de vuelos con asientos disponibles superiores a la cantidad especificada.
     *
     * @param asientos cantidad mínima de asientos disponibles
     * @return lista de vuelos
     */
    @GetMapping("/disponibles/{asientos}")
    public List<Vuelo> obtenerVuelosDisponibles(@PathVariable int asientos) {
        return vueloService.obtenerVuelosDisponibles(asientos);
    }

    /**
     * Actualiza las plazas disponibles de un vuelo.
     *
     * @param id       identificador del vuelo
     * @param cantidad cantidad de asientos a actualizar (se restan)
     */
    @PutMapping("/{id}/actualizarPlazas/{cantidad}")
    public void actualizarPlazas(@PathVariable Long id, @PathVariable int cantidad) {
        vueloService.actualizarPlazas(id, cantidad);
    }
}
