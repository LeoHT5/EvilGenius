package com.evil.genius.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

}
