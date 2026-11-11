package com.cleanship.rating.domain.model.specification;

import java.util.Objects;

public record ContainsPostalCodeSpecification(String sequence) {
  public ContainsPostalCodeSpecification {
    Objects.requireNonNull(sequence);
  }

  public static ContainsPostalCodeSpecification of(String sequence) {
    return new ContainsPostalCodeSpecification(sequence);
  }
}
