package com.agrotis.testebackend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.testebackend.model.Propriedade;
import com.agrotis.testebackend.repository.PropriedadeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class PropriedadeService {

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    public Propriedade save(@Valid Propriedade propriedade) {
        return propriedadeRepository.save(propriedade);
    }

    public Propriedade update(@Valid Propriedade propriedade) {
        if (!propriedadeRepository.existsById(propriedade.getId())){
            throw new EntityNotFoundException("Id não foi encontrado: " + propriedade.getId());
        }
        return propriedadeRepository.save(propriedade);
    }

    public void delete(UUID id) {
        if (!propriedadeRepository.existsById(id)){
            throw new EntityNotFoundException("Id não foi encontrado: " + id);
        }
        propriedadeRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return propriedadeRepository.existsById(id);
    }
}
