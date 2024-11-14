package com.evil.genius.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.evil.genius.models.Alumno;
import com.evil.genius.services.AlumnoService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @PostMapping("/registrarAlumno")
    public ResponseEntity<?> registrarAlumno(@Valid @RequestBody Alumno alumno, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en los datos enviados" + result.getAllErrors());
        }

        Alumno nuevoAlumno = alumnoService.crearAlumno(alumno);
        System.out.println("Alumno registrado con exito");

        return ResponseEntity.ok(nuevoAlumno);
    }

    @RequestMapping("/listaAlumnos")
    public ResponseEntity<List<Alumno>> obtenerListaAlumnos() {
        List<Alumno> listAlumnos = alumnoService.listarAlumnos();
        return ResponseEntity.ok(listAlumnos);
    }

    @RequestMapping("/obtenerAlumno/{id}")
    public ResponseEntity<?> obtenerAlumno(@Valid @PathVariable("id") int id) {
        Alumno buscarAlumno = alumnoService.obtenerAlumno(id);

        if (buscarAlumno == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(buscarAlumno);
    }

    @PutMapping("/actualizarAlumno/{id}")
    public ResponseEntity<?> actualizarAlumno(@Valid @RequestBody Alumno alumno, @PathVariable("id") int id,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en los datos enviados" + result.getAllErrors());
        }

        Alumno buscarAlumno = alumnoService.obtenerAlumno(id);

        if (buscarAlumno == null) {
            return ResponseEntity.notFound().build();
        }

        buscarAlumno.setNombreCompleto(alumno.getNombreCompleto());
        buscarAlumno.setApellidoPaterno(alumno.getApellidoPaterno());
        buscarAlumno.setApellidoMaterno(alumno.getApellidoMaterno());
        buscarAlumno.setGradoAlumno(alumno.getGradoAlumno());
        buscarAlumno.setSeccionAlumno(alumno.getSeccionAlumno());

        Alumno actualizarAlumno = alumnoService.actualizarAlumno(buscarAlumno);
        System.out.println("Datos actualizados del alumno");

        return ResponseEntity.ok(actualizarAlumno);
    }

    @DeleteMapping("/eliminarAlumno/{id}")
    public String eliminarAlumno(@PathVariable("id") int id) {

        alumnoService.eliminarAlumno(id);

        return "Alumno eliminado";
    }

}
