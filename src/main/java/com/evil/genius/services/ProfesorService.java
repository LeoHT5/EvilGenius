package com.evil.genius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evil.genius.implement.ProfesorImplements;
import com.evil.genius.models.Profesor;
import com.evil.genius.repository.ProfesorRepository;

@Service
public class ProfesorService implements ProfesorImplements {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    @Override
    public Profesor crearProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor actualizarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Optional<Profesor> obtenerProfesor(Long id) {
        return profesorRepository.findById(id);
    }

    @Override
    public void eliminarProfesor(Long id) {
        profesorRepository.deleteById(id);
    }

    @Override
    public Profesor deshabilitarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> listarProfesorHabilitado(boolean estado) {
        return profesorRepository.findAllByEstado(estado);
    }

}
