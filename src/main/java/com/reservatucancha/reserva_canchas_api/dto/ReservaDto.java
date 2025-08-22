package com.reservatucancha.reserva_canchas_api.dto;

import java.time.LocalDateTime;

public class ReservaDto {

    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private Long usuarioId;
    private Long canchaId;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(Long canchaId) {
        this.canchaId = canchaId;
    }
}