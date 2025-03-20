package com.valdo.reservasservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

/**
 * Entidad Reserva.
 * <p>
 * Representa una reserva de un cliente, asociando un vuelo y un hotel, junto con detalles de la fecha, total y estado.
 * </p>
 */
@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cliente", nullable = false)
    private String cliente;

    @Column(nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "id_hotel", nullable = false)
    private Integer hotelId;

    @Column(name = "id_vuelo", nullable = false)
    private Integer vueloId;

    @Column(name = "fecha_reserva", nullable = false)
    private Timestamp fechaReserva;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
}
