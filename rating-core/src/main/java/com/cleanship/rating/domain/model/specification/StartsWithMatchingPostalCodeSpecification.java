package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

import com.cleanship.rating.domain.model.location.PostalCode;

record StartsWithMatchingPostalCodeSpecification(String prefix) implements PostalCodeSpecification {
  public StartsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(prefix);
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
