package com.evil.genius.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evil.genius.DTOs.MatriculaDTO;
import com.evil.genius.models.Alumno;
import com.evil.genius.models.Curso;
import com.evil.genius.models.Matricula;
import com.evil.genius.repository.AlumnoRepository;
import com.evil.genius.repository.CursoRepository;
import com.evil.genius.services.MatriculaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private Matricula matricula;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> listarMatricula() {
        List<Matricula> listar = matriculaService.listarMatriculas();
        return ResponseEntity.ok(listar);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMatricula(@Valid @RequestBody MatriculaDTO matriculaDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.hasErrors());
        }

        Alumno alumno = alumnoRepository.findById(matriculaDTO.getCodAlumno())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Curso curso = cursoRepository.findById(matriculaDTO.getCodCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        matricula.setAlumno(alumno);
        matricula.setCurso(curso);
        matricula.setFechaMatricula(matriculaDTO.getFecha());
        matricula.setEstado(matriculaDTO.isEstado());

        Matricula registrar = matriculaService.crearMatricula(matricula);
        return ResponseEntity.ok(registrar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMatricula(@PathVariable Long id) {

        Optional<Matricula> optional = matriculaService.obtenerMatricula(id);
        return ResponseEntity.ok(optional);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMatricula(@Valid @PathVariable Long id, @RequestBody MatriculaDTO matriculaDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            ResponseEntity.badRequest().body(result.hasErrors());
        }

        Optional<Matricula> optional = matriculaService.obtenerMatricula(id);

        if (optional.isPresent()) {

            Alumno alumno = alumnoRepository.findById(matriculaDTO.getCodAlumno())
                    .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
            Curso curso = cursoRepository.findById(matriculaDTO.getCodCurso())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

            Matricula obtenerMatricula = optional.get();
            obtenerMatricula.setAlumno(alumno);
            obtenerMatricula.setCurso(curso);
            obtenerMatricula.setFechaMatricula(matriculaDTO.getFecha());

            Matricula actualizar = matriculaService.actualizarMatricula(obtenerMatricula);

            return ResponseEntity.ok(actualizar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Matricula con ID " + id + " no encontrado");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMatricula(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        return ResponseEntity.ok("Matricula con " + id + " eliminado exitosamente");
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarHabilitados(@PathVariable String estado) {
        boolean nuevoEstado;

        if (estado.equalsIgnoreCase("habilitados")) {
            nuevoEstado = true;
        } else if (estado.equalsIgnoreCase("deshabilitados")) {
            nuevoEstado = false;
        } else {
            return ResponseEntity.badRequest().body(null);
        }

        List<Matricula> habilitados = matriculaService.listarHabilitador(nuevoEstado);
        return ResponseEntity.ok(habilitados);
    }

}
