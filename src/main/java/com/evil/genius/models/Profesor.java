package com.evil.genius.models;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Component
@Getter
@Setter
@Table(name = "tb_profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codProfesor")
    public Long codProfesor;

    @Column(name = "nombres")
    @NotEmpty(message = "Ingresar nombres")
    public String nombres;

    @Column(name = "apellidoPaterno")
    @NotEmpty(message = "Ingresar apellido paterno")
    public String apellidoPaterno;

    @Column(name = "apellidoMaterno")
    @NotEmpty(message = "Ingresar apellido materno")
    public String apellidoMaterno;

    @Column(name = "correo")
    @NotEmpty(message = "Ingresar correo")
    public String correo;

    @Column(name = "password")
    @NotEmpty(message = "Ingresar contraseña")
    public String password;

    @Column(name = "estado")
    public boolean estado;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos;
}
