package com.evil.genius.implement;

import java.util.List;
import java.util.Optional;

import com.evil.genius.models.Matricula;

public interface MatriculaImplements {

    public List<Matricula> listarMatriculas();

    public Matricula crearMatricula(Matricula matricula);

    public Matricula actualizarMatricula(Matricula matricula);

    public Optional<Matricula> obtenerMatricula(Long id);

    public void eliminarMatricula(Long id);

    public Matricula deshabilitarMatricula(Matricula matricula);

    public List<Matricula> listarHabilitador(boolean estado);
}
