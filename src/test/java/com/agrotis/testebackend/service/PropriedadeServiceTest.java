package com.agrotis.testebackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agrotis.testebackend.model.Propriedade;
import com.agrotis.testebackend.repository.PropriedadeRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PropriedadeServiceTest {

    @Autowired
    private PropriedadeService propriedadeService;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    private Propriedade propriedade;

    private static final String DEFAULT_NOME = "Pro1";
    private static final String UPDATE_NOME = "Pro2";

    private static final String DEFAULT_CNPJ = "95935125000110";

    @BeforeEach
    public void init() {
        propriedadeRepository.deleteAll();
        
        propriedade = new Propriedade();
        propriedade.setNome(DEFAULT_NOME);
        propriedade.setCnpj(DEFAULT_CNPJ);
    }

    @Test
    public void testSavePropriedade() {
        propriedadeService.save(propriedade);

        assertThat(propriedadeRepository.findOneByNome(DEFAULT_NOME)).isPresent();
    }

    @Test
    public void testUpdatePropriedade() {
        propriedadeService.save(propriedade);
        assertThat(propriedadeRepository.findOneByNome(DEFAULT_NOME)).isPresent();

        propriedade.setNome(UPDATE_NOME);
        propriedadeService.update(propriedade);
        assertThat(propriedadeRepository.findOneByNome(DEFAULT_NOME)).isNotPresent();
        assertThat(propriedadeRepository.findOneByNome(UPDATE_NOME)).isPresent();
    }

    @Test
    public void testDeletePropriedade() {
        propriedade = propriedadeService.save(propriedade);
        assertThat(propriedadeRepository.findOneByNome(DEFAULT_NOME)).isPresent();
        propriedadeService.delete(propriedade.getId());
        assertThat(propriedadeRepository.findOneByNome(DEFAULT_NOME)).isNotPresent();
    }
}
