package com.evil.genius.implement;

import java.util.List;
import java.util.Optional;

import com.evil.genius.models.Notas;

public interface NotasImplements {

    public List<Notas> listarNotas();

    public Notas crearNotas(Notas notas);

    public Notas actualizarNotas(Notas notas);

    public Optional<Notas> obtenerNotas(Long id);

    public void eliminarNotas(Long id);

    public Notas deshabilitarNotas(Notas notas);

    public List<Notas> listarHabilitados(boolean estado);
}
