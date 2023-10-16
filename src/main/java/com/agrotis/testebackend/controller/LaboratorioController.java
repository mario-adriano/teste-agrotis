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

import com.agrotis.testebackend.model.Laboratorio;
import com.agrotis.testebackend.service.LaboratorioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Laboratorio> createLaboratorio(@Valid @RequestBody Laboratorio laboratorio) {
        return new ResponseEntity<>(laboratorioService.save(laboratorio), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Laboratorio> updateLaboratorio(@Valid @RequestBody Laboratorio laboratorio) {
        return new ResponseEntity<>(laboratorioService.update(laboratorio), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLaboratorio(@PathVariable UUID id) {
        laboratorioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
