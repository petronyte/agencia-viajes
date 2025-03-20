package com.valdo.hotelesservice.repository;

import com.valdo.hotelesservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad Hotel.
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    /**
     * Encuentra todos los hoteles que estén disponibles.
     *
     * @return lista de hoteles disponibles
     */
    List<Hotel> findByDisponibleTrue();
}
