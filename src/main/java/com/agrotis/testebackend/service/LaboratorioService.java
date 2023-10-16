package com.agrotis.testebackend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.testebackend.model.Laboratorio;
import com.agrotis.testebackend.repository.LaboratorioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Laboratorio save(@Valid Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio update(@Valid Laboratorio laboratorio) {
        if (!laboratorioRepository.existsById(laboratorio.getId())){
            throw new EntityNotFoundException("Id não foi encontrado: " + laboratorio.getId());
        }
        return laboratorioRepository.save(laboratorio);
    }

    public void delete(UUID id) {
        if (!laboratorioRepository.existsById(id)){
            throw new EntityNotFoundException("Id não foi encontrado: " + id);
        }
        laboratorioRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return laboratorioRepository.existsById(id);
    }
}
