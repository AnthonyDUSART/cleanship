package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidEndsWithMatchingPostalCodeSpecification extends DomainException {
  private InvalidEndsWithMatchingPostalCodeSpecification(String suffix) {
    Objects.requireNonNull(suffix);

    super(String.format("Ends with matching postal code specification \"%s\" is invalid", suffix));
  }

  public static InvalidEndsWithMatchingPostalCodeSpecification of(String suffix) {
    return new InvalidEndsWithMatchingPostalCodeSpecification(suffix);
  }
}
