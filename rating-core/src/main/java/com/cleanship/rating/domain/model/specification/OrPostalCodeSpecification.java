package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

public record OrPostalCodeSpecification(
    PostalCodeSpecification left, PostalCodeSpecification right) {

  public OrPostalCodeSpecification {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
  }

  public static OrPostalCodeSpecification of(
      PostalCodeSpecification left, PostalCodeSpecification right) {
    return new OrPostalCodeSpecification(left, right);
  }
}
