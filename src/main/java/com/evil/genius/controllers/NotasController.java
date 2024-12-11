package com.evil.genius.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evil.genius.models.Alumno;
import com.evil.genius.models.Curso;
import com.evil.genius.models.Matricula;
import com.evil.genius.models.Notas;
import com.evil.genius.repository.AlumnoRepository;
import com.evil.genius.repository.CursoRepository;
import com.evil.genius.repository.MatriculaRepository;
import com.evil.genius.services.AlumnoService;
import com.evil.genius.services.NotasService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/notas")
public class NotasController {

    @Autowired
    private NotasService notasService;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @RequestMapping("/listar")
    public ResponseEntity<?> listarNotas() {
        List<Notas> lista = notasService.listarNotas();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarNotas(@Valid @RequestBody Notas notas, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.hasErrors());
        }

        Alumno alumno = alumnoRepository.findById(notas.getAlumno().getCodAlumno())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Curso curso = cursoRepository.findById(notas.getCurso().getCodCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Matricula matricula = matriculaRepository.findById(notas.getMatricula().getCodMatricula())
                .orElseThrow(() -> new RuntimeException("Matricula no encontrado"));

        notas.setAlumno(alumno);
        notas.setCurso(curso);
        notas.setMatricula(matricula);

        Notas registro = notasService.crearNotas(notas);
        return ResponseEntity.ok(registro);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> obtenerNotas(@PathVariable Long id) {

        Optional<Notas> notas = notasService.obtenerNotas(id);

        return ResponseEntity.ok(notas);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarNotas(@Valid @PathVariable Long id, @RequestBody Notas notas,
            BindingResult result) {
        if (result.hasErrors()) {
            ResponseEntity.badRequest().body(result.hasErrors());
        }

        Optional<Notas> optional = notasService.obtenerNotas(id);
        if (optional.isPresent()) {
            Notas obtenerNotas = optional.get();
            obtenerNotas.setAlumno(obtenerNotas.getAlumno());
            obtenerNotas.setCurso(obtenerNotas.getCurso());
            obtenerNotas.setMatricula(obtenerNotas.getMatricula());
            obtenerNotas.setNota(obtenerNotas.getNota());
            obtenerNotas.setFecha(obtenerNotas.getFecha());

            Notas actualizar = notasService.actualizarNotas(obtenerNotas);

            return ResponseEntity.ok(actualizar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error notas con ID: " + id + " no encontrado");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarNotas(@PathVariable Long id) {

        notasService.eliminarNotas(id);
        return ResponseEntity.ok("Nota: " + id + " Eliminado exitosamente");
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarHabilitados(@PathVariable String estado) {

        boolean nuevoEstado;

        if (estado.equalsIgnoreCase("habilitados")) {
            nuevoEstado = true;
        } else if (estado.equalsIgnoreCase("deshabilitado")) {
            nuevoEstado = false;
        } else {
            return ResponseEntity.badRequest().body(null);
        }

        List<Notas> habilitado = notasService.listarHabilitados(nuevoEstado);

        return ResponseEntity.ok(habilitado);
    }

}
