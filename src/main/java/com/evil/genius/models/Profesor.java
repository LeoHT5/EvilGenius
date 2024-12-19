package com.evil.genius.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codProfesor")
    private Long codProfesor;

    @Column(name = "nombres")
    @NotEmpty(message = "Ingresar nombres")
    private String nombres;

    @Column(name = "apellidoPaterno")
    @NotEmpty(message = "Ingresar apellido paterno")
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno")
    @NotEmpty(message = "Ingresar apellido materno")
    private String apellidoMaterno;

    @OneToOne
    @JoinColumn(name = "codUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "estado")
    private boolean estado;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos;
}
