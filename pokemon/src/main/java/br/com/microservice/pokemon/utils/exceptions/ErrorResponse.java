package br.com.microservice.pokemon.utils.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private Integer httStatusCode;
    private String path;
    private Instant timeTamp;
    private String error;
}
