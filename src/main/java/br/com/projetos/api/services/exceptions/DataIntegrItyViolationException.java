package br.com.projetos.api.services.exceptions;

public class DataIntegrItyViolationException extends RuntimeException{

    public DataIntegrItyViolationException(String message) {

        super(message);
    }
}
