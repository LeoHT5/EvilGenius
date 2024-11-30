package com.evil.genius.models;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
@Entity
@Getter
@Setter
@Table(name = "tb_Curso")
public class Curso {

    @Id
    @Column(name = "codCurso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long codCurso;

    @Column(name = "nombre")
    @NotEmpty(message = "Ingresar nombre")
    public String nombre;

    @Column(name = "nivelAcademico")
    @NotEmpty(message = "Seleccionar nicel academico")
    public String nivelAcademico;

    @Column(name = "descripcion")
    public String descripcion;

    @Column(name = "estado")
    public boolean estado;

    @ManyToOne
    @JoinColumn(name = "codProfesor", nullable = false)
    public Profesor profesor;

}
