package com.agrotis.testebackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

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
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid4")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_inicial")
    private LocalDate dataInicial;

    @Column(name = "data_final")
    private LocalDate dataFinal;

    @Lob
    @Column(name = "observacoes")
    private String observacoes;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
