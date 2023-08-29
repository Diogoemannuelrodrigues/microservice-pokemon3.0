package br.com.microservice.pokemon.utils.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Slf4j
@ControllerAdvice
public class CommonsErrorHandlers {
    @ExceptionHandler(TreinadorNaoEncontradoException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> treinadorNaoEncontradoExceptionResponseEntity(TreinadorNaoEncontradoException e, HttpServletRequest request){
        log.error("TreinadorException: message -> {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeTamp(Instant.now());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setError("Treinador nao encontrado. ");

        return ResponseEntity.status(errorResponse.getHttStatusCode()).body(errorResponse);
    }
}
