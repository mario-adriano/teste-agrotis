package com.agrotis.testebackend.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.agrotis.testebackend.model.Laboratorio;
import com.agrotis.testebackend.model.Pessoa;

import jakarta.persistence.criteria.Join;

public class LaboratorioSpecification {

    public static Specification<Laboratorio> temDataInicialBetween(LocalDate start, LocalDate end) {
        return (root, query, criteriaBuilder) -> {
            Join<Laboratorio, Pessoa> join = root.join("pessoas");
            return criteriaBuilder.between(join.get("dataInicial"), start, end);
        };
    }

    public static Specification<Laboratorio> temDataFinalBetween(LocalDate start, LocalDate end) {
        return (root, query, criteriaBuilder) -> {
            Join<Laboratorio, Pessoa> join = root.join("pessoas");
            return criteriaBuilder.between(join.get("dataFinal"), start, end);
        };
    }

    public static Specification<Laboratorio> temObservacoesContaining(String keyword) {
        return (root, query, criteriaBuilder) -> {
            Join<Laboratorio, Pessoa> join = root.join("pessoas");
            return criteriaBuilder.like(join.get("observacoes"), "%" + keyword + "%");
        };
    }

    public static Specification<Laboratorio> temQuantidadePessoasGreaterThanOrEqual(Long quantidade) {
        return (root, query, criteriaBuilder) -> {
            Join<Laboratorio, Pessoa> join = root.join("pessoas");
            query.groupBy(root.get("id"));
            query.having(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.count(join.get("id")), quantidade));
            return query.getRestriction();
        };
    }
}


