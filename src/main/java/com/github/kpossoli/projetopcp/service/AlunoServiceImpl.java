package com.github.kpossoli.projetopcp.service;

import java.util.List;

import com.github.kpossoli.projetopcp.dto.AlunoDto;
import com.github.kpossoli.projetopcp.mapper.AlunoMapper;
import com.github.kpossoli.projetopcp.mapper.TurmaMapper;
import com.github.kpossoli.projetopcp.model.Usuario;
import com.github.kpossoli.projetopcp.repository.PapelRepository;
import com.github.kpossoli.projetopcp.repository.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.model.Pontuacao;
import com.github.kpossoli.projetopcp.repository.AlunoRepository;
import com.github.kpossoli.projetopcp.repository.NotaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final NotaRepository notaRepository;
    private final PapelRepository papelRepository;
    private final UsuarioService usuarioService;
    private final TurmaMapper turmaMapper;
    private final TurmaRepository turmaRepository;
    private final AlunoMapper alunoMapper;

    @Override
    public Aluno obter(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }


    @Override
    public Aluno criar(Aluno aluno) {
        log.info("Criando aluno", aluno);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(aluno.getNomeCompleto());
        novoUsuario.setEmail(aluno.getEmail());
        novoUsuario.setSenha(aluno.getSenha());
        novoUsuario.setPapel(papelRepository.findByNome("ALUNO").orElseThrow(() -> new RuntimeException("Papel não encontrado")));

        Usuario usuarioSalvo = usuarioService.criarUsuario(novoUsuario);

        aluno.setUsuario(usuarioSalvo);

        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno atualizar(Long id, Aluno aluno) {
        log.info("Atualizando aluno de id: {}", id);

        Aluno alunoSalvo = obter(id);
        BeanUtils.copyProperties(aluno, alunoSalvo, "id");
        return alunoRepository.save(alunoSalvo);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo aluno de id: {}", id);

        Aluno aluno = obter(id);

        if(aluno.getUsuario() != null) {
            usuarioService.excluir(aluno.getUsuario().getId());
        }

        alunoRepository.deleteById(id);
    }

    @Override
    public Pontuacao obterPontuacao(Long id) {
        List<Nota> notas = notaRepository.findByAlunoId(id);

        BigDecimal totalNotas = BigDecimal.valueOf(notas.size());

        BigDecimal somaNotas = notas.stream()
                .map(Nota::getNota)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal pontuacao = somaNotas
                .divide(totalNotas)
                .multiply(BigDecimal.TEN);

        Pontuacao pontuacaoAluno = new Pontuacao();
        pontuacaoAluno.setPontuacao(pontuacao);

        return pontuacaoAluno;
    }

    @Override
    public List<Nota> listarNotas(Long id) {
        Aluno aluno = obter(id);
        return notaRepository.findByAlunoId(aluno.getId());
    }

    @Override
    public List<AlunoDto> pegarAlunosPorTurma(Long idTurma) {
        List<Aluno> alunos = alunoRepository.findByTurmaId(idTurma);
        return alunoMapper.toDto(alunos);
    }

}
