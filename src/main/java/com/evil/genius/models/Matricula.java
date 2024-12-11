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

@Entity
@Component
@Data
@Getter
@Setter
@Table(name = "tb_Matricula")
public class Matricula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codMatricula")
  private Long codMatricula;

  @ManyToOne
  @JoinColumn(name = "codAlumno", nullable = false)
  private Alumno alumno;

  @ManyToOne
  @JoinColumn(name = "codCurso", nullable = false)
  private Curso curso;

  @Column(name = "fechaMatricula")
  private Date fechaMatricula;

  @Column(name = "estado")
  private boolean estado;
}
