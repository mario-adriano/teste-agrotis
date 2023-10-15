package com.agrotis.testebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.agrotis.testebackend.model.Pessoal;

public interface PessoaRepository extends JpaRepository<Pessoal, Long>, JpaSpecificationExecutor<Pessoal> {
    
}
