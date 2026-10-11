package br.com.alisson.cp2.sistemacursos.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alisson.cp2.sistemacursos.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: o recurso procurado não existe
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 400: o corpo enviado falhou na validação (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<ErrorResponse.CampoErro> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(campo -> new ErrorResponse.CampoErro(
                        campo.getField(),
                        campo.getDefaultMessage()))
                .toList();

        ErrorResponse resposta = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Erro de validação",
                erros
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}