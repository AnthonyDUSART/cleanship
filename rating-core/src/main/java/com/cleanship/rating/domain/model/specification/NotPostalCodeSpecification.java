package com.cleanship.rating.domain.model.specification;

public record NotPostalCodeSpecification(PostalCodeSpecification postalCodeSpecification) {
  public static NotPostalCodeSpecification of(PostalCodeSpecification postalCodeSpecification) {
    return new NotPostalCodeSpecification(postalCodeSpecification);
  }
}
