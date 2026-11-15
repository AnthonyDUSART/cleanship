package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidContainsMatchingPostalCodeSpecificationException extends DomainException {
  private InvalidContainsMatchingPostalCodeSpecificationException(String suffix) {
    Objects.requireNonNull(suffix);

    super(String.format("Contains matching postal code specification \"%s\" is invalid", suffix));
  }

  public static InvalidContainsMatchingPostalCodeSpecificationException of(String suffix) {
    return new InvalidContainsMatchingPostalCodeSpecificationException(suffix);
  }
}
