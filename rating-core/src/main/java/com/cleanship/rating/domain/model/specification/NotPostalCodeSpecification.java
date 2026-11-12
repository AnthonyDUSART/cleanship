package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

import com.cleanship.rating.domain.model.location.PostalCode;

record NotPostalCodeSpecification(PostalCodeSpecification postalCodeSpecification)
    implements PostalCodeSpecification {
  public NotPostalCodeSpecification {
    Objects.requireNonNull(postalCodeSpecification);
  }

  public static NotPostalCodeSpecification of(PostalCodeSpecification postalCodeSpecification) {
    return new NotPostalCodeSpecification(postalCodeSpecification);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return !postalCodeSpecification.isSatisfiedBy(postalCode);
  }
}
