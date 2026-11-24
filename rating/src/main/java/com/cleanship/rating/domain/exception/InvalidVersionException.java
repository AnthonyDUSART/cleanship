package com.cleanship.rating.domain.exception;

public final class InvalidVersionException extends DomainException {
  private InvalidVersionException(long value) {
    super(String.format("Version \"%d\" is invalid", value));
  }

  public static InvalidVersionException of(long value) {
    return new InvalidVersionException(value);
  }
}
