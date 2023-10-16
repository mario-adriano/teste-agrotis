package com.agrotis.testebackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.agrotis.testebackend.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID>, JpaSpecificationExecutor<Pessoa> {
    
    public Optional<Pessoa> findOneByNome(String nome);
}
