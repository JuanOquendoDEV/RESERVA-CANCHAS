package com.reservatucancha.reserva_canchas_api.service;

import com.reservatucancha.reserva_canchas_api.entity.Reserva;
import com.reservatucancha.reserva_canchas_api.entity.Cancha;
import com.reservatucancha.reserva_canchas_api.entity.Usuario;
import com.reservatucancha.reserva_canchas_api.repository.ReservaRepository;
import com.reservatucancha.reserva_canchas_api.service.CanchaService;
import com.reservatucancha.reserva_canchas_api.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CanchaService canchaService;
    private final UsuarioService usuarioService;

    public ReservaService(ReservaRepository reservaRepository, CanchaService canchaService, UsuarioService usuarioService) {
        this.reservaRepository = reservaRepository;
        this.canchaService = canchaService;
        this.usuarioService = usuarioService;
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    // Métodos para buscar por ID
    public Optional<Usuario> findUsuarioById(Long id) {
        return usuarioService.findById(id);
    }

    public Optional<Cancha> findCanchaById(Long id) {
        return canchaService.findById(id);
    }
}