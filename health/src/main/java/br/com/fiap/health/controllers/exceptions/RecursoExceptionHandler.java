package br.com.fiap.health.controllers.exceptions;


import br.com.fiap.health.services.exceptions.RecursoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.Instant;

@ControllerAdvice
public class RecursoExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroValidacao> recursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroValidacao err = new ErroValidacao();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setErro("Recurso não encontrado");
        err.setMensagem(e.getMessage());
        err.setCaminho(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroValidacao> unico(DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErroValidacao err = new ErroValidacao();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setErro("Violação de integridade");
        err.setMensagem("Esse cpf já existe");
        err.setCaminho(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroValidacao> unico(ConstraintViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErroValidacao err = new ErroValidacao();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setErro("Violação de integridade");
        err.setMensagem(e.getMessage());
        err.setCaminho(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErroValidacao err = new ErroValidacao();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setErro("Erro de validação");
        err.setMensagem(e.getMessage());
        err.setCaminho(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }
}
