package com.example.projeto_spring.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabledUser(DisabledException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário desativado.");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuth(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro de autenticação.");
    }

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

    @ExceptionHandler(DuplicatePlayerPerformanceException.class)
    public ResponseEntity duplicatePlayerPerformanceException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(InvalidShirtNumberException.class)
    public ResponseEntity invalidShirtNumberException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(InvalidTransferWindowException.class)
    public ResponseEntity invalidTransferWindowException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MatchOnSameDateException.class)
    public ResponseEntity matchOnSameDateException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(PlayerNotInMatchTeamException.class)
    public ResponseEntity playerNotInMatchTeamException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(RatingNotBetweenZeroAndTen.class)
    public ResponseEntity ratingNotBetweenZeroAndTen() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity validacaoException() {
        return ResponseEntity.badRequest().build();
    }

    public record DtoErro400(String campo, String mensagem) {
        public DtoErro400(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
