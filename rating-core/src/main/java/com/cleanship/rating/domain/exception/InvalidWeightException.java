package com.cleanship.rating.domain.exception;

public final class InvalidWeightException extends DomainException {
  private InvalidWeightException(int grams) {
    super(String.format("Weight \"%d\" is invalid", grams));
  }

  public static InvalidWeightException of(int grams) {
    return new InvalidWeightException(grams);
  }
}
