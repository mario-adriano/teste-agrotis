package com.agrotis.testebackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "propriedade")
@Entity
public class Propriedade implements Persistable<UUID>{

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @NonNull
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório")
    @NonNull
    private String nome;

    @Column(name = "cnpj")
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(min = 14, max = 14)
    @CNPJ
    @NonNull
    private String cnpj;

    @OneToMany(mappedBy="propriedade", fetch = FetchType.LAZY)
    private Set<Pessoa> pessoas;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }
}
