package com.evil.genius.implement;

import java.util.List;
import java.util.Optional;

import com.evil.genius.models.Curso;

public interface CursoImplements {

    public List<Curso> listarCursos();

    public Curso crearCurso(Curso curso);

    public Curso actualizarCurso(Curso curso);

    public Optional<Curso> obtenerCurso(Long id);

    public void eliminarCurso(Long id);

    public Curso desabilitarCurso(Curso curso);

    public List<Curso> listarHabilitado(boolean estado);

}
