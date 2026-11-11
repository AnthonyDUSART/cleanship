package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

public record StartsWithMatchingPostalCodeSpecification(String prefix) {
  public StartsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(prefix);
  }

  public static StartsWithMatchingPostalCodeSpecification of(String prefix) {
    return new StartsWithMatchingPostalCodeSpecification(prefix);
  }
}
