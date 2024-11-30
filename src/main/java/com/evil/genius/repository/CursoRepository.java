package com.evil.genius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evil.genius.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
