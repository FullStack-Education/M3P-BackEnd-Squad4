package com.github.kpossoli.projetopcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.kpossoli.projetopcp.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
}