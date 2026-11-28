package com.cleanship.rating.core.model.specification;

import com.cleanship.rating.core.exception.InvalidStartsWithMatchingPostalCodeSpecificationException;
import com.cleanship.rating.core.model.location.PostalCode;
import java.util.Locale;
import java.util.Objects;

public record StartsWithMatchingPostalCodeSpecification(String prefix)
    implements PostalCodeSpecification {
  public StartsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(prefix);

    prefix = prefix.strip().toUpperCase(Locale.ROOT);

    if (prefix.isBlank()) {
      throw InvalidStartsWithMatchingPostalCodeSpecificationException.of(prefix);
    }
  }

  public static StartsWithMatchingPostalCodeSpecification of(String prefix) {
    return new StartsWithMatchingPostalCodeSpecification(prefix);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return postalCode.startsWith(prefix);
  }
}
