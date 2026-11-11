package com.cleanship.rating.domain.exception;

public final class InvalidZoneNameException extends DomainException {
  private InvalidZoneNameException(String value) {
    super(String.format("Zone name \"%s\" is invalid", value));
  }

  public static InvalidZoneNameException of(String value) {
    return new InvalidZoneNameException(value);
  }
}
