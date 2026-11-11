package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

public record AndPostalCodeSpecification(
    PostalCodeSpecification left, PostalCodeSpecification right) {
  public AndPostalCodeSpecification {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
  }

  public static AndPostalCodeSpecification of(
      PostalCodeSpecification left, PostalCodeSpecification right) {
    return new AndPostalCodeSpecification(left, right);
  }
}
