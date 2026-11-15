package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.exception.InvalidStartsWithMatchingPostalCodeSpecificationException;
import com.cleanship.rating.domain.model.location.PostalCode;
import java.util.Objects;

public record StartsWithMatchingPostalCodeSpecification(String prefix)
    implements PostalCodeSpecification {
  public StartsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(prefix);

    prefix = prefix.strip();

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
