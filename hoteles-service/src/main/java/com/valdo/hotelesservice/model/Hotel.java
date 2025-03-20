package com.valdo.hotelesservice.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad Hotel.
 * <p>
 * Representa la información de un hotel, incluyendo nombre, ubicación,
 * categoría (estrellas), precio por noche y su disponibilidad.
 * </p>
 */
@Entity
@Table(name = "hoteles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private int estrellas;

    @Column(name = "precio_por_noche", nullable = false)
    private double precioPorNoche;

    @Column(name = "disponibilidad", nullable = false)
    private boolean disponible;
}
