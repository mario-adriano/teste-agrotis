package com.agrotis.testebackend.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DuplicateExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException constraintException = (ConstraintViolationException) ex.getCause();
            if (constraintException.getConstraintName().equals("uq_cnpj")) {
                return new ResponseEntity<>("CNPJ já existe no sistema.", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
