package com.evil.genius.implement;

import java.util.List;

import com.evil.genius.models.Alumno;

public interface AlumnoImplements {

    public Alumno crearAlumno(Alumno alumno);

    public List<Alumno> listarAlumnos();

    public Alumno actualizarAlumno(Alumno alumno);

    public Alumno obtenerAlumno(Integer id);

    public String eliminarAlumno(Integer id);
}
