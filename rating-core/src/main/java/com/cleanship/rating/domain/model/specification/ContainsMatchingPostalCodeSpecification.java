package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.location.PostalCode;
import java.util.Objects;

record ContainsMatchingPostalCodeSpecification(String sequence) implements PostalCodeSpecification {
  public ContainsMatchingPostalCodeSpecification {
    Objects.requireNonNull(sequence);
  }

  public static ContainsMatchingPostalCodeSpecification of(String sequence) {
    return new ContainsMatchingPostalCodeSpecification(sequence);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    return postalCode.contains(sequence);
  }
}
