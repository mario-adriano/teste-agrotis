package com.agrotis.testebackend.dto;

import java.util.UUID;

import com.agrotis.testebackend.model.Laboratorio;

import lombok.Getter;

@Getter
public class LaboratorioDTO {
    private UUID id;
    private String nome;
    private Long quantidadePessoas;

    public LaboratorioDTO(Laboratorio laboratorio) {
        this.id = laboratorio.getId();
        this.nome = laboratorio.getNome().toUpperCase();
        this.quantidadePessoas = (long) laboratorio.getPessoas().size();
    }
}

