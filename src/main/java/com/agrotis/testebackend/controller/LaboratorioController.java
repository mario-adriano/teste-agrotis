package com.agrotis.testebackend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.testebackend.dto.LaboratorioDTO;
import com.agrotis.testebackend.model.Laboratorio;
import com.agrotis.testebackend.service.LaboratorioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @ResponseBody
    @RequestMapping("/all")
    public List<Laboratorio> listar() {
        List<Laboratorio> laboratorios = laboratorioService.findAll();
        return laboratorios;
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<LaboratorioDTO>> filtrar(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicialStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicialEnd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinalStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinalEnd,
            @RequestParam(required = false) String observacoesKeyword,
            @RequestParam Long quantidadeMinima) {
        
        List<Laboratorio> laboratorios = laboratorioService.filtrarLaboratorios(dataInicialStart, dataInicialEnd, dataFinalStart, dataFinalEnd, observacoesKeyword, quantidadeMinima);
        
        List<LaboratorioDTO> laboratorioDTOs = laboratorios.stream()
                .map(LaboratorioDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(laboratorioDTOs);
    }

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
