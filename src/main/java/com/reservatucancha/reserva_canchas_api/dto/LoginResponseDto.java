package com.reservatucancha.reserva_canchas_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String message;
}