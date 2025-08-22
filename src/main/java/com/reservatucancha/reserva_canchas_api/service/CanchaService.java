package com.reservatucancha.reserva_canchas_api.service;

import com.reservatucancha.reserva_canchas_api.entity.Cancha;
import com.reservatucancha.reserva_canchas_api.repository.CanchaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanchaService {

    private final CanchaRepository canchaRepository;

    public CanchaService(CanchaRepository canchaRepository) {
        this.canchaRepository = canchaRepository;
    }

    public List<Cancha> findAll() {
        return canchaRepository.findAll();
    }

    public Optional<Cancha> findById(Long id) {
        return canchaRepository.findById(id);
    }

    public Cancha save(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    public void deleteById(Long id) {
        canchaRepository.deleteById(id);
    }
}