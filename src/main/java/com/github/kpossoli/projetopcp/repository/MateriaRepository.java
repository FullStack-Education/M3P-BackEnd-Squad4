package com.github.kpossoli.projetopcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.kpossoli.projetopcp.model.Materia;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    List<Materia> findCursoById(Long cursoId);

}