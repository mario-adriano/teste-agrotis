package com.agrotis.testebackend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.agrotis.testebackend.model.Laboratorio;
import com.agrotis.testebackend.repository.LaboratorioRepository;
import com.agrotis.testebackend.specification.LaboratorioSpecification;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public List<Laboratorio> findAll() {
        return laboratorioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Laboratorio> filtrarLaboratorios(LocalDate dataInicialStart, LocalDate dataInicialEnd, LocalDate dataFinalStart, LocalDate dataFinalEnd, String observacoesKeyword, Long quantidadeMinima) {
        Specification<Laboratorio> spec = Specification.where(null);

        if (dataInicialStart != null && dataInicialEnd != null) {
            spec = spec.and(LaboratorioSpecification.temDataInicialBetween(dataInicialStart, dataInicialEnd));
        }
        if (dataFinalStart != null && dataFinalEnd != null) {
            spec = spec.and(LaboratorioSpecification.temDataFinalBetween(dataFinalStart, dataFinalEnd));
        }
        if (observacoesKeyword != null) {
            spec = spec.and(LaboratorioSpecification.temObservacoesContaining(observacoesKeyword));
        }
        spec = spec.and(LaboratorioSpecification.temQuantidadePessoasGreaterThanOrEqual(quantidadeMinima));

        List<Laboratorio> laboratorios = laboratorioRepository.findAll(spec);
        laboratorios.forEach(l -> Hibernate.initialize(l.getPessoas()));

        return laboratorios;
    }

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
