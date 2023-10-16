package com.agrotis.testebackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
@Entity
public class Pessoa implements Persistable<UUID>{

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Column(name = "data_inicial")
    @NotNull(message = "Data Inicial é obrigatório")
    private LocalDate dataInicial;

    @Column(name = "data_final")
    @NotNull(message = "Data Final é obrigatório")
    private LocalDate dataFinal;

    @Column(name = "observacoes")
    private String observacoes;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }
}
