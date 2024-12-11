package com.evil.genius.models;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
@Entity
@Setter
@Getter
@Table(name = "tb_notas")
public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codNotas")
    private Long codNotas;

    @ManyToOne
    @JoinColumn(name = "codAlumno", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "codCurso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "codMatricula", nullable = false)
    private Matricula matricula;

    @Column(name = "nota")
    private int nota;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private boolean estado;
}
