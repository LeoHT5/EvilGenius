package com.evil.genius.DTOs;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotasDTO {
    private int codAlumno;
    private Long codCurso;
    private Long codMatricula;
    private int nota;
    private Date fecha;
    private boolean estado;
}
