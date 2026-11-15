package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.exception.InvalidEndsWithMatchingPostalCodeSpecificationException;
import com.cleanship.rating.domain.model.location.PostalCode;
import java.util.Locale;
import java.util.Objects;

public record EndsWithMatchingPostalCodeSpecification(String suffix)
    implements PostalCodeSpecification {
  public EndsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(suffix);

    suffix = suffix.strip().toUpperCase(Locale.ROOT);

    if (suffix.isBlank()) {
      throw InvalidEndsWithMatchingPostalCodeSpecificationException.of(suffix);
    }
  }

  public static EndsWithMatchingPostalCodeSpecification of(String suffix) {
    return new EndsWithMatchingPostalCodeSpecification(suffix);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return postalCode.endsWith(suffix);
  }
}
