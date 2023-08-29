package br.com.microservice.pokemon.utils.exceptions;

public class TreinadorNaoEncontradoException extends RuntimeException {
    public TreinadorNaoEncontradoException(String message) {
        super(message);
    }
}
