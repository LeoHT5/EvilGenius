package com.evil.genius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evil.genius.implement.CursoImplements;
import com.evil.genius.models.Curso;
import com.evil.genius.repository.CursoRepository;

@Service
public class CursoService implements CursoImplements {

    @Autowired
    public CursoRepository cursoRepository;

    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Optional<Curso> obtenerCurso(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public Curso desabilitarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> listarHabilitado(boolean estado) {
        return cursoRepository.findAllByEstado(estado);
    }

}
