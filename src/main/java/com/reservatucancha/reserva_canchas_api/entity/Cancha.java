package com.reservatucancha.reserva_canchas_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera getters, setters, toString, equals y hashCode de Lombok
@NoArgsConstructor // Genera un constructor sin argumentos de Lombok
@AllArgsConstructor // Genera un constructor con todos los argumentos de Lombok
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "cancha") // Mapea la clase a una tabla llamada "cancha"
public class Cancha {

    @Id // Marca el campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación de la clave primaria
    private Long id;

    @Column(name = "nombre") // Mapea el campo a una columna llamada "nombre"
    private String nombre;

    @Column(name = "tipo_cancha") // Mapea el campo a una columna llamada "tipo_cancha"
    private String tipoCancha; // Ej: "Fútbol 5", "Fútbol 7"

    @Column(name = "precio_por_hora") // Mapea el campo a una columna llamada "precio_por_hora"
    private double precioPorHora;

}