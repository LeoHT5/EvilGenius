package com.evil.genius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evil.genius.implement.MatriculaImplements;
import com.evil.genius.models.Matricula;
import com.evil.genius.repository.MatriculaRepository;

@Service
public class MatriculaService implements MatriculaImplements {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public List<Matricula> listarMatriculas() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula crearMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public Matricula actualizarMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public Optional<Matricula> obtenerMatricula(Long id) {
        return matriculaRepository.findById(id);
    }

    @Override
    public void eliminarMatricula(Long id) {
        matriculaRepository.deleteById(id);
    }

    @Override
    public Matricula deshabilitarMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public List<Matricula> listarHabilitador(boolean estado) {
        return matriculaRepository.findAllByEstado(estado);
    }

}
