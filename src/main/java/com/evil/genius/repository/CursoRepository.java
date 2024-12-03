package com.evil.genius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evil.genius.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findAllByEstado(boolean estado);

}
