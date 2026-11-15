package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidSubdivisionNameException extends DomainException {
  private InvalidSubdivisionNameException(String value) {
    Objects.requireNonNull(value);

    super(String.format("Subdivision name \"%s\" is invalid", value));
  }

  public static InvalidSubdivisionNameException of(String value) {
    return new InvalidSubdivisionNameException(value);
  }
}
