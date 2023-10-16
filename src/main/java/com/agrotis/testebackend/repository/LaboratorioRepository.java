package com.agrotis.testebackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.agrotis.testebackend.model.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, UUID>, JpaSpecificationExecutor<Laboratorio> {
    
    public Optional<Laboratorio> findOneByNome(String nome);
}
