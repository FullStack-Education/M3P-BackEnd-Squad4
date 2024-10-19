package com.github.kpossoli.projetopcp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.kpossoli.projetopcp.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByAlunoId(Long id);
    
}