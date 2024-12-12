package com.evil.genius.models;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
    private int codAlumno;

    @Column(name = "nombreCompleto")
    @NotEmpty(message = "Ingresar nombres completos")
    private String nombreCompleto;

    @Column(name = "apellidoMaterno")
    @NotEmpty(message = "Ingresar apellido Materno")
    private String apellidoMaterno;

    @Column(name = "apellidoPaterno")
    @NotEmpty(message = "Ingresar apellido Paterno")
    private String apellidoPaterno;

    @Column(name = "grado")
    @NotEmpty(message = "Ingresar grado del Alumno")
    private String grado;

    @Column(name = "seccion")
    @NotEmpty(message = "Ingresar seccion del Alumno")
    private String seccion;

    @Column(name = "estadoAlumno")
    private boolean estado;

    @Column(name = "nivelAcademico")
    @NotEmpty(message = "Seleccionar nivel academico")
    private String nivelAcademico;

    @Column(name = "correo")
    @Email(message = "Ingresar correo electronico")
    private String correo;

    @Column(name = "password")
    @NotEmpty(message = "Ingresar contraseña")
    private String password;

}