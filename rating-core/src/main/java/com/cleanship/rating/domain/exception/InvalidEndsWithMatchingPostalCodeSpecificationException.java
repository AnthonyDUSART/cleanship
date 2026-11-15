package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidEndsWithMatchingPostalCodeSpecificationException extends DomainException {
  private InvalidEndsWithMatchingPostalCodeSpecificationException(String suffix) {
    Objects.requireNonNull(suffix);

    super(String.format("Ends with matching postal code specification \"%s\" is invalid", suffix));
  }

  public static InvalidEndsWithMatchingPostalCodeSpecificationException of(String suffix) {
    return new InvalidEndsWithMatchingPostalCodeSpecificationException(suffix);
  }
}
