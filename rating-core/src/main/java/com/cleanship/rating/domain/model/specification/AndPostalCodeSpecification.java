package com.cleanship.rating.domain.model.specification;

public record AndPostalCodeSpecification(
    PostalCodeSpecification left, PostalCodeSpecification right) {
  public static AndPostalCodeSpecification of(
      PostalCodeSpecification left, PostalCodeSpecification right) {
    return new AndPostalCodeSpecification(left, right);
  }
}
