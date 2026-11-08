package com.cleanship.rating.domain.exception;

public final class InvalidPercentageException extends DomainException {
  private InvalidPercentageException(int valueInMilliPercent) {
    super(String.format("Percentage \"%d\" is invalid", valueInMilliPercent));
  }

  public static InvalidPercentageException of(int valueInMilliPercent) {
    return new InvalidPercentageException(valueInMilliPercent);
  }
}
