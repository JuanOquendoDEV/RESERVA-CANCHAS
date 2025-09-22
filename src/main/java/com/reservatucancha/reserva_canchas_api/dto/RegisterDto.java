package com.reservatucancha.reserva_canchas_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String identificacion;
    private String telefono;
}