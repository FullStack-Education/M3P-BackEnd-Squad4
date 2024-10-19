package com.github.kpossoli.projetopcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.kpossoli.projetopcp.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
}