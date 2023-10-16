package com.agrotis.testebackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.testebackend.model.Pessoa;
import com.agrotis.testebackend.service.PessoaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Pessoa> updatePessoa(@Valid @RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.update(pessoa), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable UUID id) {
        pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
