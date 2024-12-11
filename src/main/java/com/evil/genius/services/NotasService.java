package com.evil.genius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evil.genius.implement.NotasImplements;
import com.evil.genius.models.Notas;
import com.evil.genius.repository.NotasRepository;

@Service
public class NotasService implements NotasImplements {

    @Autowired
    private NotasRepository notasRepository;

    @Override
    public List<Notas> listarNotas() {
        return notasRepository.findAll();
    }

    @Override
    public Notas crearNotas(Notas notas) {
        return notasRepository.save(notas);
    }

    @Override
    public Notas actualizarNotas(Notas notas) {
        return notasRepository.save(notas);
    }

    @Override
    public Optional<Notas> obtenerNotas(Long id) {
        return notasRepository.findById(id);
    }

    @Override
    public void eliminarNotas(Long id) {
        notasRepository.deleteById(id);
    }

    @Override
    public Notas deshabilitarNotas(Notas notas) {
        return notasRepository.save(notas);
    }

    @Override
    public List<Notas> listarHabilitados(boolean estado) {
        return notasRepository.findAllByEstado(estado);
    }

}
