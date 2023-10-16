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
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "propriedade")
@Entity
public class Propriedade implements Persistable<UUID>{

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Column(name = "cnpj")
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(min = 14, max = 14)
    @CNPJ
    private String cnpj;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }
}
