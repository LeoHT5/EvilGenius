package com.evil.genius.implement;

import java.util.List;
import java.util.Optional;

import com.evil.genius.models.Profesor;

public interface ProfesorImplements {

    public List<Profesor> listarProfesores();

    public Profesor crearProfesor(Profesor profesor);

    public Profesor actualizarProfesor(Profesor profesor);

    public Optional<Profesor> obtenerProfesor(Long id);

    public void eliminarProfesor(Long id);

    public Profesor deshabilitarProfesor(Profesor profesor);

    public List<Profesor> listarProfesorHabilitado(boolean estado);
}
