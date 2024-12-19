package com.evil.genius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evil.genius.models.Notas;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

    List<Notas> findAllByEstado(boolean estado);

}
