package com.github.kpossoli.projetopcp.repository;

import com.github.kpossoli.projetopcp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.kpossoli.projetopcp.model.Aluno;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByTurmaId(Long turmaId);

    Optional<Aluno> findByEmail(String email);

}