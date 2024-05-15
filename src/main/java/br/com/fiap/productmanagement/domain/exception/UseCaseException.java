package br.com.fiap.productmanagement.domain.exception;

public class UseCaseException extends  RuntimeException {

  public UseCaseException(String message) {

    super(message);

  }

}