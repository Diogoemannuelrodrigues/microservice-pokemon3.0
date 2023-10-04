package br.com.microservice.pokemon.utils.exceptions;

public class TrackingException extends RuntimeException {
    public TrackingException(String message) {
        super(message);
    }
}
