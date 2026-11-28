package com.cleanship.rating.core.model.specification;

import com.cleanship.rating.core.exception.InvalidEqualsMatchingPostalCodeSpecificationException;
import com.cleanship.rating.core.model.location.PostalCode;
import java.util.Locale;
import java.util.Objects;

public record EqualsMatchingPostalCodeSpecification(String value)
    implements PostalCodeSpecification {
  public EqualsMatchingPostalCodeSpecification {
    Objects.requireNonNull(value);

    value = value.strip().toUpperCase(Locale.ROOT);

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
