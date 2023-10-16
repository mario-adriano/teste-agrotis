package com.agrotis.testebackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agrotis.testebackend.model.Laboratorio;
import com.agrotis.testebackend.repository.LaboratorioRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LaboratorioServiceTest {

    @Autowired
    private LaboratorioService laboratorioService;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    private Laboratorio laboratorio;

    private static final String DEFAULT_NOME = "Lab1";
    private static final String UPDATE_NOME = "Lab2";

    @BeforeEach
    public void init() {
        laboratorioRepository.deleteAll();
        
        laboratorio = new Laboratorio();
        laboratorio.setNome(DEFAULT_NOME);
    }

    @Test
    public void testSaveLaboratorio() {
        laboratorioService.save(laboratorio);

        assertThat(laboratorioRepository.findOneByNome(DEFAULT_NOME)).isPresent();
    }

    @Test
    public void testUpdateLaboratorio() {
        laboratorioService.save(laboratorio);
        assertThat(laboratorioRepository.findOneByNome(DEFAULT_NOME)).isPresent();

        laboratorio.setNome(UPDATE_NOME);
        laboratorioService.update(laboratorio);
        assertThat(laboratorioRepository.findOneByNome(DEFAULT_NOME)).isNotPresent();
        assertThat(laboratorioRepository.findOneByNome(UPDATE_NOME)).isPresent();
    }

    @Test
    public void testDeleteLaboratorio() {
        laboratorio = laboratorioService.save(laboratorio);
        assertThat(laboratorioRepository.findOneByNome(DEFAULT_NOME)).isPresent();
        laboratorioService.delete(laboratorio.getId());
        assertThat(laboratorioRepository.findOneByNome(DEFAULT_NOME)).isNotPresent();
    }
}
