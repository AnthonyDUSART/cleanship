package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

record EndsWithMatchingPostalCodeSpecification(String suffix) implements PostalCodeSpecification {
  public EndsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(suffix);
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
