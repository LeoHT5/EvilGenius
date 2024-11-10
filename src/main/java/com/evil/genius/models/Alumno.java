package com.evil.genius.models;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Data
@Setter
@Getter
@Table(name = "tb_Alumno")
public class Alumno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codAlumno")
    public int codAlumno;

    @Column(name = "nombreCompleto")
    @NotEmpty(message = "Ingresar nombres completos")
    public String nombreCompleto;

    @Column(name = "apellidoMaterno")
    @NotEmpty(message = "Ingresar apellido Materno")
    public String apellidoMaterno;

    @Column(name = "apellidoPaterno")
    @NotEmpty(message = "Ingresar apellido Paterno")
    public String apellidoPaterno;

    @Column(name = "gradoAlumno")
    @NotEmpty(message = "Ingresar grado del Alumno")
    public String gradoAlumno;

    @Column(name = "seccionAlumno")
    @NotEmpty(message = "Ingresar seccion del Alumno")
    public String seccionAlumno;

}