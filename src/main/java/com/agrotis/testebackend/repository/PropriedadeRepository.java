package com.agrotis.testebackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.agrotis.testebackend.model.Propriedade;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, UUID>, JpaSpecificationExecutor<Propriedade> {
    
    public Optional<Propriedade> findOneByNome(String nome);
}
