package com.cleanship.rating.domain.model.specification;

public record OrPostalCodeSpecification(
    PostalCodeSpecification left, PostalCodeSpecification right) {
  public static OrPostalCodeSpecification of(
      PostalCodeSpecification left, PostalCodeSpecification right) {
    return new OrPostalCodeSpecification(left, right);
  }
}
