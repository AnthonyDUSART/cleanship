package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidEqualsMatchingPostalCodeSpecificationException extends DomainException {
  private InvalidEqualsMatchingPostalCodeSpecificationException(String value) {
    Objects.requireNonNull(value);

    super(String.format("Equals matching postal code specification \"%s\" is invalid", value));
  }

  public static InvalidEqualsMatchingPostalCodeSpecificationException of(String value) {
    return new InvalidEqualsMatchingPostalCodeSpecificationException(value);
  }
}
