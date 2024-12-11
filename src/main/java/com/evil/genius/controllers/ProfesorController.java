package com.evil.genius.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evil.genius.models.Profesor;
import com.evil.genius.services.ProfesorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    // @Autowired
    // private Profesor profesor;

    @RequestMapping("/listar")
    public ResponseEntity<?> listarProfesores() {
        List<Profesor> profesor = profesorService.listarProfesores();
        return ResponseEntity.ok(profesor);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProfesor(@Valid @RequestBody Profesor profesor, BindingResult result) {

        Profesor crearProfesor = profesorService.crearProfesor(profesor);

        return ResponseEntity.ok(crearProfesor);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> obtenerProfesor(@PathVariable("id") Long id) {

        Optional<Profesor> obtenerProfesor = profesorService.obtenerProfesor(id);

        if (obtenerProfesor.isPresent()) {
            return ResponseEntity.ok(obtenerProfesor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Profesor con ID " + id + " no encontrado");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarProfesor(@Valid @PathVariable("id") Long id, @RequestBody Profesor profesor,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Optional<Profesor> optionalProfesor = profesorService.obtenerProfesor(id);

        if (optionalProfesor.isPresent()) {
            Profesor obtenerProfesor = optionalProfesor.get();
            obtenerProfesor.setNombres(profesor.getNombres());
            obtenerProfesor.setApellidoPaterno(profesor.getApellidoPaterno());
            obtenerProfesor.setApellidoMaterno(profesor.getApellidoMaterno());
            obtenerProfesor.setCorreo(profesor.getCorreo());
            obtenerProfesor.setPassword(profesor.getPassword());

            Profesor actualizarDatos = profesorService.actualizarProfesor(obtenerProfesor);
            return ResponseEntity.ok(actualizarDatos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Profesor con ID " + id + " no encontrado");
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProfesor(@PathVariable("id") Long id) {

        Optional<Profesor> profesorOptional = profesorService.obtenerProfesor(id);

        if (profesorOptional.isPresent()) {
            profesorService.eliminarProfesor(id);
            return ResponseEntity.ok("Profesor con ID " + id + " elimnado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Profesor con ID " + id + " no encontrado");
        }
    }

    @PutMapping("/estado/deshabilitar/{id}")
    public ResponseEntity<?> deshabilitarProfesor(@PathVariable("id") Long id) {

        Optional<Profesor> optionalProfesor = profesorService.obtenerProfesor(id);

        if (optionalProfesor.isPresent()) {
            Profesor obtenerProfesor = optionalProfesor.get();
            obtenerProfesor.setEstado(false);
            return ResponseEntity.ok(obtenerProfesor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Profesor con ID " + id + " no encontrado");
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarHabilitado(@PathVariable("estado") String estado) {

        boolean nuevoEstado;

        if (estado.equalsIgnoreCase("habilitados")) {
            nuevoEstado = true;
        } else if (estado.equalsIgnoreCase("deshabilitado")) {
            nuevoEstado = false;
        } else {
            return ResponseEntity.badRequest().body(null);
        }

        List<Profesor> listado = profesorService.listarProfesorHabilitado(nuevoEstado);

        return ResponseEntity.ok(listado);
    }

}
