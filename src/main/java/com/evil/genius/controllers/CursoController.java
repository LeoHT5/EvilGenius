package com.evil.genius.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evil.genius.models.Curso;
import com.evil.genius.models.Profesor;
import com.evil.genius.services.CursoService;
import com.evil.genius.services.ProfesorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ProfesorService profesorService;

    @RequestMapping("/listar")
    public ResponseEntity<?> listarCursos() {

        List<Curso> lista = cursoService.listarCursos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCurso(@Valid @RequestBody Curso curso, BindingResult result) {

        if (result.hasErrors()) {
            ResponseEntity.badRequest().body(result.hasErrors());
        }

        Profesor profesor = profesorService.obtenerProfesor(curso.getProfesor().getCodProfesor())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        curso.setProfesor(profesor);

        Curso crearCurso = cursoService.crearCurso(curso);
        return ResponseEntity.ok(crearCurso);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> obtenerCurso(@PathVariable("id") Long id) {

        Optional<Curso> obtenerCurso = cursoService.obtenerCurso(id);

        if (obtenerCurso.isPresent()) {
            return ResponseEntity.ok(obtenerCurso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Curso con ID " + id + " no encontrado.");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCurso(@Valid @PathVariable("id") Long id, @RequestBody Curso curso,
            BindingResult result) {

        if (result.hasErrors()) {
            ResponseEntity.badRequest().body(result.hasErrors());
        }

        Optional<Curso> optionalCurso = cursoService.obtenerCurso(id);

        if (optionalCurso.isPresent()) {
            Curso obtenerCurso = optionalCurso.get();
            obtenerCurso.setNombre(curso.getNombre());
            obtenerCurso.setNivelAcademico(curso.getNivelAcademico());
            obtenerCurso.setDescripcion(curso.getDescripcion());
            obtenerCurso.setEstado(curso.isEstado());
            obtenerCurso.setProfesor(curso.getProfesor());

            Curso editar = cursoService.actualizarCurso(obtenerCurso);
            return ResponseEntity.ok(editar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Curso con ID " + id + " no encontrado.");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable("id") Long id) {

        cursoService.eliminarCurso(id);
        return ResponseEntity.ok("Curso: " + id + " eliminado exitosamente.");
    }

    @PutMapping("/estado/deshabilitar/{id}")
    public ResponseEntity<?> deshabilitarCurso(@PathVariable("id") Long id) {

        Optional<Curso> optionalCurso = cursoService.obtenerCurso(id);

        if (optionalCurso.isPresent()) {
            Curso obtenerCurso = optionalCurso.get();
            obtenerCurso.setEstado(false);
            return ResponseEntity.ok(obtenerCurso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Curso con ID " + id + " no encontrado");
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarHabilitado(@PathVariable("estado") String estado) {
        boolean nuevoEstado;

        if (estado.equalsIgnoreCase("habilitados")) {
            nuevoEstado = true;
        } else if (estado.equalsIgnoreCase("deshabilitados")) {
            nuevoEstado = false;
        } else {
            return ResponseEntity.badRequest().body(null);
        }

        List<Curso> habilitados = cursoService.listarHabilitado(nuevoEstado);

        return ResponseEntity.ok(habilitados);
    }

}
