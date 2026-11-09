package com.cleanship.rating.domain.exception;

public final class InvalidSideException extends DomainException {
  private InvalidSideException(int valueInMillimeter) {
    super(String.format("Side \"%d\" is invalid", valueInMillimeter));
  }

  public static InvalidSideException of(int valueInMillimeter) {
    return new InvalidSideException(valueInMillimeter);
  }
}
