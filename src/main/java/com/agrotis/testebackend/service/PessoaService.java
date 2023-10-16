package com.agrotis.testebackend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.testebackend.model.Pessoa;
import com.agrotis.testebackend.repository.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa save(@Valid Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(@Valid Pessoa pessoa) {
        if (!pessoaRepository.existsById(pessoa.getId())){
            throw new EntityNotFoundException("Id não foi encontrado: " + pessoa.getId());
        }
        return pessoaRepository.save(pessoa);
    }

    public void delete(UUID id) {
        if (!pessoaRepository.existsById(id)){
            throw new EntityNotFoundException("Id não foi encontrado: " + id);
        }
        pessoaRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return pessoaRepository.existsById(id);
    }
}
