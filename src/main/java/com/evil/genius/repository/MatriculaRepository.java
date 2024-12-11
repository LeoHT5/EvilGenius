package com.evil.genius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evil.genius.models.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findAllByEstado(boolean estado);

}
