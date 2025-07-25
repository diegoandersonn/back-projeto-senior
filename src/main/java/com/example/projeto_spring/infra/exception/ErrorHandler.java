package com.example.projeto_spring.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DtoErro400::new).toList());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        var body = new HashMap<>();
        body.put("status", 400);
        body.put("erro", "Bad Request");
        body.put("mensagem", ex.getMessage());
        body.put("caminho", request.getRequestURI());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity dataIntegrityViolationException(DataIntegrityViolationException ex) {
        var mensagemDetalhada = "Erro de integridade de dados. Verifique se as informações preenchidas são válidas.";

        var causa = ex.getRootCause();
        if (causa.getMessage() != null) {
            var causaMsg = causa.getMessage();

            if (causaMsg.contains("violates foreign key constraint") && causaMsg.contains("fk_time")) {
                mensagemDetalhada = "Erro: o time informado não existe no banco de dados.";
            } else if (causaMsg.contains("null value in column")) {
                mensagemDetalhada = "Erro: existe um campo obrigatório que não foi preenchido.";
            } else if (causaMsg.contains("duplicate key")) {
                mensagemDetalhada = "Erro: esse registro já existe.";
            }
        }

        return ResponseEntity.badRequest().body(Map.of("erro", mensagemDetalhada));
    }

    public record DtoErro400(String campo, String mensagem) {
        public DtoErro400(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
