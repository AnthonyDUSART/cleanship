package com.cleanship.rating.domain.model.specification;

public record ContainsPostalCodeSpecification(String sequence) {
  public static ContainsPostalCodeSpecification of(String sequence) {
    return new ContainsPostalCodeSpecification(sequence);
  }
}
