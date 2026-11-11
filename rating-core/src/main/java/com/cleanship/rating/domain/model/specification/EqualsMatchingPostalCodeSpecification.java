package com.cleanship.rating.domain.model.specification;

public record EqualsMatchingPostalCodeSpecification(String value) {
  public static EqualsMatchingPostalCodeSpecification of(String value) {
    return new EqualsMatchingPostalCodeSpecification(value);
  }
}
