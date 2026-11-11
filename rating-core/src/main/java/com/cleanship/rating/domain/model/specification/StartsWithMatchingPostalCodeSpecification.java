package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

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
