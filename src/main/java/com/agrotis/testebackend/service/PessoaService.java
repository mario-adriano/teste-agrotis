package com.agrotis.testebackend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.agrotis.testebackend.model.Pessoa;
import com.agrotis.testebackend.repository.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;

public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void delete(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + id));
        pessoaRepository.delete(pessoa);
    }
}
