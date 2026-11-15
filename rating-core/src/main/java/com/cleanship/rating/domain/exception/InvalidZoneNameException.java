package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidZoneNameException extends DomainException {
  private InvalidZoneNameException(String value) {
    Objects.requireNonNull(value);

    super(String.format("Zone name \"%s\" is invalid", value));
  }

  public static InvalidZoneNameException of(String value) {
    return new InvalidZoneNameException(value);
  }
}
