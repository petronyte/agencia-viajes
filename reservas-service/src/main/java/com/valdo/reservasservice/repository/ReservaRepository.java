package com.valdo.reservasservice.repository;

import com.valdo.reservasservice.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad Reserva.
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    /**
     * Encuentra reservas asociadas a un DNI específico.
     *
     * @param dni el DNI del cliente
     * @return lista de reservas
     */
    List<Reserva> findByDni(String dni);
}
