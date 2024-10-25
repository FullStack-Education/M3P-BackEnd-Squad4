package com.github.kpossoli.projetopcp.repository;

import com.github.kpossoli.projetopcp.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.kpossoli.projetopcp.model.Papel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {

    Optional<Papel> findByNome(String nome);

}