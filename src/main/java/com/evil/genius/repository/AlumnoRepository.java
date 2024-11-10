package com.evil.genius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evil.genius.models.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}