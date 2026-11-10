package com.cleanship.rating.domain.exception;

public final class InvalidSideException extends DomainException {
  private InvalidSideException(int millimeters) {
    super(String.format("Side \"%d\" is invalid", millimeters));
  }

  public static InvalidSideException of(int millimeters) {
    return new InvalidSideException(millimeters);
  }
}
