package com.evil.genius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evil.genius.implement.AlumnoImplements;
import com.evil.genius.models.Alumno;
import com.evil.genius.repository.AlumnoRepository;

@Service
public class AlumnoService implements AlumnoImplements {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno crearAlumno(Alumno alumno) {
        alumno = alumnoRepository.save(alumno);
        return alumno;
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno actualizarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno obtenerAlumno(Integer id) {
        return alumnoRepository.findById(id).get();
    }

    @Override
    public String eliminarAlumno(Integer id) {
        alumnoRepository.deleteById(id);
        return "Alumno eliminado";
    }

}
