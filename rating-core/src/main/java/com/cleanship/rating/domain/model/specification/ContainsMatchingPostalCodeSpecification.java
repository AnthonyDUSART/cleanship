package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

import com.cleanship.rating.domain.model.location.PostalCode;

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
