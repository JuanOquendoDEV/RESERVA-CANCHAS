package com.reservatucancha.reserva_canchas_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    // Relación de muchos a uno: muchas reservas pueden ser hechas por un solo usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación de muchos a uno: muchas reservas pueden ser para una sola cancha
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;

}