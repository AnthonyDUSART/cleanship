package com.cleanship.rating.domain.model.zone;

import com.cleanship.rating.domain.model.Country;
import com.cleanship.rating.domain.model.specification.PostalCodeSpecification;

public record Subdivision(
    SubdivisionName name, Country country, PostalCodeSpecification postalCodeSpecification) {
  public static Subdivision of(
      SubdivisionName name, Country country, PostalCodeSpecification postalCodeSpecification) {
    return new Subdivision(name, country, postalCodeSpecification);
  }
}
