package com.reservatucancha.reserva_canchas_api.repository;

import com.reservatucancha.reserva_canchas_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}