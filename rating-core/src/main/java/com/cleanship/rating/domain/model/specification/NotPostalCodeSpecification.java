package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

public record NotPostalCodeSpecification(PostalCodeSpecification postalCodeSpecification) {
  public NotPostalCodeSpecification {
    Objects.requireNonNull(postalCodeSpecification);
  }

  public static NotPostalCodeSpecification of(PostalCodeSpecification postalCodeSpecification) {
    return new NotPostalCodeSpecification(postalCodeSpecification);
  }
}
