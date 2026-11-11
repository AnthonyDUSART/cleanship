package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

public record NotPostalCodeSpecification(PostalCodeSpecification postalCodeSpecification)
    implements PostalCodeSpecification {
  public NotPostalCodeSpecification {
    Objects.requireNonNull(postalCodeSpecification);
  }

  public static NotPostalCodeSpecification of(PostalCodeSpecification postalCodeSpecification) {
    return new NotPostalCodeSpecification(postalCodeSpecification);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    return !postalCodeSpecification.isSatisfiedBy(postalCode);
  }
}
