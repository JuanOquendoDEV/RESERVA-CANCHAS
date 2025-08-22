package com.reservatucancha.reserva_canchas_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
@Entity // Indica que esta es una entidad JPA
@Table(name = "usuario") // Mapea la clase a una tabla llamada "usuario"
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email", unique = true) // El email debe ser único
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "identificacion", unique = true) // La identificación debe ser única
    private String identificacion;

    @Column(name = "telefono")
    private String telefono;

}