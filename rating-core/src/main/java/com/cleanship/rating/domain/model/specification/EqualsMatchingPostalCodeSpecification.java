package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.exception.InvalidEqualsMatchingPostalCodeSpecificationException;
import com.cleanship.rating.domain.model.location.PostalCode;
import java.util.Objects;

public record EqualsMatchingPostalCodeSpecification(String value)
    implements PostalCodeSpecification {
  public EqualsMatchingPostalCodeSpecification {
    Objects.requireNonNull(value);

    value = value.strip();

    if (value.isBlank()) {
      throw InvalidEqualsMatchingPostalCodeSpecificationException.of(value);
    }
  }

  public static EqualsMatchingPostalCodeSpecification of(String value) {
    return new EqualsMatchingPostalCodeSpecification(value);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return postalCode.isEqualTo(value);
  }
}
