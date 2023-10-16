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

import com.agrotis.testebackend.model.Propriedade;
import com.agrotis.testebackend.service.PropriedadeService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/propriedade")
public class PropriedadeController {

    @Autowired
    private PropriedadeService propriedadeService;

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Propriedade> createPropriedade(@Valid @RequestBody Propriedade propriedade) {
        return new ResponseEntity<>(propriedadeService.save(propriedade), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Propriedade> updatePropriedade(@Valid @RequestBody Propriedade propriedade) {
        return new ResponseEntity<>(propriedadeService.update(propriedade), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePropriedade(@PathVariable UUID id) {
        propriedadeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
