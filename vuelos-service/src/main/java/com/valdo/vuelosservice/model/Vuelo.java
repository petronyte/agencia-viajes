package com.valdo.vuelosservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidad Vuelo.
 * <p>
 * Representa un vuelo, incluyendo aerolínea, origen, destino, fecha y hora de salida,
 * precio y número de asientos disponibles.
 * </p>
 */
@Entity
@Table(name = "vuelos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "aerolinea", nullable = false)
    private String aerolinea;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    @Column(nullable = false)
    private double precio;

    @Column(name = "asientos_disponibles", nullable = false)
    private int asientosDisponibles;
}
