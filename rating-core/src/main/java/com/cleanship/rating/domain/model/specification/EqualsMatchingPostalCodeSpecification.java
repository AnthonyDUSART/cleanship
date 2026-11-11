package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

record EqualsMatchingPostalCodeSpecification(String value) implements PostalCodeSpecification {
  public EqualsMatchingPostalCodeSpecification {
    Objects.requireNonNull(value);
  }

  public static EqualsMatchingPostalCodeSpecification of(String value) {
    return new EqualsMatchingPostalCodeSpecification(value);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(value);

    return postalCode.isEqualTo(value);
  }
}
