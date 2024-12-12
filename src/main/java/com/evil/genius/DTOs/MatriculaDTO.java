package com.evil.genius.DTOs;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaDTO {

    private int codAlumno;
    private Long codCurso;
    private Date fecha;
    private boolean estado;

}
