package com.reservatucancha.reserva_canchas_api.repository;

import com.reservatucancha.reserva_canchas_api.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}