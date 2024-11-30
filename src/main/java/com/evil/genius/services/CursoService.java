package com.evil.genius.services;

import java.util.List;

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
    public Curso obtenerCurso(Long id) {
        return cursoRepository.findById(id).get();
    }

    @Override
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso desabilitarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

}
