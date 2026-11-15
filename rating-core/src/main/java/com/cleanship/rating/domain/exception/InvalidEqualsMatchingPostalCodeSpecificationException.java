package com.cleanship.rating.domain.exception;

public final class InvalidEqualsMatchingPostalCodeSpecificationException extends DomainException {
  private InvalidEqualsMatchingPostalCodeSpecificationException(String value) {
    super(String.format("Equals matching postal code specification \"%s\" is invalid", value));
  }

  public static InvalidEqualsMatchingPostalCodeSpecificationException of(String value) {
    return new InvalidEqualsMatchingPostalCodeSpecificationException(value);
  }
}
