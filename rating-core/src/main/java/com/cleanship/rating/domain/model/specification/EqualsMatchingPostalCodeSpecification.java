package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

public record EqualsMatchingPostalCodeSpecification(String value) {
  public EqualsMatchingPostalCodeSpecification {
    Objects.requireNonNull(value);
  }

  public static EqualsMatchingPostalCodeSpecification of(String value) {
    return new EqualsMatchingPostalCodeSpecification(value);
  }
}
