package com.cleanship.rating.domain.model.specification;

public record StartsWithMatchingPostalCodeSpecification(String prefix) {
  public static StartsWithMatchingPostalCodeSpecification of(String prefix) {
    return new StartsWithMatchingPostalCodeSpecification(prefix);
  }
}
