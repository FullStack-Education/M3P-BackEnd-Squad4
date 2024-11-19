package com.github.kpossoli.projetopcp.repository;

import com.github.kpossoli.projetopcp.dto.CursoSimplifiedDto;
import com.github.kpossoli.projetopcp.model.Materia;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.kpossoli.projetopcp.model.Curso;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @EntityGraph(attributePaths = "materias")
    Optional<Curso> findById(Long id);

}