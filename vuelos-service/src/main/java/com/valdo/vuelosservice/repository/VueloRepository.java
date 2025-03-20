package com.valdo.vuelosservice.repository;

import com.valdo.vuelosservice.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad Vuelo.
 */
@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    /**
     * Encuentra vuelos con asientos disponibles mayores que la cantidad especificada.
     *
     * @param asientos cantidad mínima de asientos
     * @return lista de vuelos
     */
    List<Vuelo> findByAsientosDisponiblesGreaterThan(int asientos);
}
