package com.cleanship.rating.domain.model.specification;

public record EndsWithMatchingPostalCodeSpecification(String suffix) {
  public static EndsWithMatchingPostalCodeSpecification of(String suffix) {
    return new EndsWithMatchingPostalCodeSpecification(suffix);
  }
}
