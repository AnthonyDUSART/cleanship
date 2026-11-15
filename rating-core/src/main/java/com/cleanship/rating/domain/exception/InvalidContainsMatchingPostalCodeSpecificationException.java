package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidContainsMatchingPostalCodeSpecificationException extends DomainException {
  private InvalidContainsMatchingPostalCodeSpecificationException(String sequence) {
    Objects.requireNonNull(sequence);

    super(String.format("Contains matching postal code specification \"%s\" is invalid", sequence));
  }

  public static InvalidContainsMatchingPostalCodeSpecificationException of(String sequence) {
    return new InvalidContainsMatchingPostalCodeSpecificationException(sequence);
  }
}
