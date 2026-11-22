package com.cleanship.rating.domain.exception;

public final class EmptyRateBookException extends DomainException {
  private EmptyRateBookException() {
    super("Rate book must contain at least one rate chart");
  }

  public static EmptyRateBookException of() {
    return new EmptyRateBookException();
  }
}
