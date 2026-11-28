package com.cleanship.rating.core.exception;

import java.util.Objects;

public final class InvalidStartsWithMatchingPostalCodeSpecificationException
    extends DomainException {
  private InvalidStartsWithMatchingPostalCodeSpecificationException(String prefix) {
    Objects.requireNonNull(prefix);

    super(
        String.format("Starts with matching postal code specification \"%s\" is invalid", prefix));
  }

  public static InvalidStartsWithMatchingPostalCodeSpecificationException of(String prefix) {
    return new InvalidStartsWithMatchingPostalCodeSpecificationException(prefix);
  }
}
