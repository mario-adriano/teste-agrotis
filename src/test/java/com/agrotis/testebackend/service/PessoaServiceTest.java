package com.agrotis.testebackend.service;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agrotis.testebackend.model.Laboratorio;
import com.agrotis.testebackend.model.Pessoa;
import com.agrotis.testebackend.model.Propriedade;
import com.agrotis.testebackend.repository.LaboratorioRepository;
import com.agrotis.testebackend.repository.PessoaRepository;
import com.agrotis.testebackend.repository.PropriedadeRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private LaboratorioRepository laboratorioRepository;
    @Autowired
    private PropriedadeRepository propriedadeRepository;

    private Pessoa pessoa;

    private static final String DEFAULT_NOME = "Richard John Grayson";
    private static final String UPDATE_NOME = "Dick Grayson";

    private static final LocalDate DEFAULT_DATA_INICIAL = LocalDate.now();

    private static final LocalDate DEFAULT_DATA_FINAL = LocalDate.now().plusDays(10);

    private static final String DEFAULT_OBSERVACOES = "night Wing";

    @BeforeEach
    public void init() {
        pessoaRepository.deleteAll();
        laboratorioRepository.deleteAll();
        propriedadeRepository.deleteAll();

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNome("lab1");
        laboratorioRepository.save(laboratorio);

        Propriedade propriedade = new Propriedade();
        propriedade.setNome("pro1");
        propriedade.setCnpj("95935125000110");
        propriedadeRepository.save(propriedade);
        
        pessoa = new Pessoa();
        pessoa.setNome(DEFAULT_NOME);
        pessoa.setDataInicial(DEFAULT_DATA_INICIAL);
        pessoa.setDataFinal(DEFAULT_DATA_FINAL);
        pessoa.setObservacoes(DEFAULT_OBSERVACOES);
        pessoa.setLaboratorio(laboratorio);
        pessoa.setPropriedade(propriedade);
    }


    @Test
    public void testSavePessoa() {
        pessoaService.save(pessoa);

        assertThat(pessoaRepository.findOneByNome(DEFAULT_NOME)).isPresent();
    }

    @Test
    public void testUpdatePessoa() {
        pessoaService.save(pessoa);
        assertThat(pessoaRepository.findOneByNome(DEFAULT_NOME)).isPresent();

        pessoa.setNome(UPDATE_NOME);
        pessoaService.update(pessoa);
        assertThat(pessoaRepository.findOneByNome(DEFAULT_NOME)).isNotPresent();
        assertThat(pessoaRepository.findOneByNome(UPDATE_NOME)).isPresent();
    }

    @Test
    public void testDeletePessoa() {
        pessoa = pessoaService.save(pessoa);
        assertThat(pessoaRepository.findOneByNome(DEFAULT_NOME)).isPresent();
        pessoaService.delete(pessoa.getId());
        assertThat(pessoaRepository.findOneByNome(DEFAULT_NOME)).isNotPresent();
    }
}
