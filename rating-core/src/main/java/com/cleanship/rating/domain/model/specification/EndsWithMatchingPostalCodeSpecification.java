package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

public record EndsWithMatchingPostalCodeSpecification(String suffix) {
  public EndsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(suffix);
  }

  public static EndsWithMatchingPostalCodeSpecification of(String suffix) {
    return new EndsWithMatchingPostalCodeSpecification(suffix);
  }
}
