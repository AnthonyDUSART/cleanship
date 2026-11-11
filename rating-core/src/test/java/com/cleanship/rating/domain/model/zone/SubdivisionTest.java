package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.Country;
import com.cleanship.rating.domain.model.Location;
import com.cleanship.rating.domain.model.PostalCode;
import com.cleanship.rating.domain.model.specification.PostalCodeSpecification;
import org.junit.jupiter.api.Test;

final class SubdivisionTest {
  private static final SubdivisionName NAME = SubdivisionName.of("Paris");

  private static final Country COUNTRY = Country.of("FR");

  private static final PostalCodeSpecification SPECIFICATION =
      PostalCodeSpecification.isEqualTo("75000");

  @Test
  void should_store_name() {
    Subdivision subdivision = Subdivision.of(NAME, COUNTRY, SPECIFICATION);

    assertEquals(NAME, subdivision.name());
  }

  @Test
  void should_store_country() {
    Subdivision subdivision = Subdivision.of(NAME, COUNTRY, SPECIFICATION);

    assertEquals(COUNTRY, subdivision.country());
  }

  @Test
  void should_store_specification() {
    Subdivision subdivision = Subdivision.of(NAME, COUNTRY, SPECIFICATION);

    assertEquals(SPECIFICATION, subdivision.postalCodeSpecification());
  }

  @Test
  void should_throw_exception_when_name_is_null() {
    assertThrows(NullPointerException.class, () -> Subdivision.of(null, COUNTRY, SPECIFICATION));
  }

  @Test
  void should_throw_exception_when_country_is_null() {
    assertThrows(NullPointerException.class, () -> Subdivision.of(NAME, null, SPECIFICATION));
  }

  @Test
  void should_throw_exception_when_specification_is_null() {
    assertThrows(NullPointerException.class, () -> Subdivision.of(NAME, COUNTRY, null));
  }

  @Test
  void should_store_any_specification_when_is_created_without_specification() {
    Subdivision subdivision = Subdivision.of(NAME, COUNTRY);

    assertEquals(PostalCodeSpecification.any(), subdivision.postalCodeSpecification());
  }

  @Test
  void should_be_satisfied() {
    Location paris = Location.of(COUNTRY, PostalCode.of("75000"));

    Subdivision subdivision = Subdivision.of(NAME, COUNTRY, SPECIFICATION);

    assertTrue(subdivision.isSatisfiedBy(paris));
  }

  @Test
  void should_not_be_satisfied_when_postal_code_does_not_match() {
    Location france = Location.of(COUNTRY);

    Subdivision subdivision = Subdivision.of(NAME, COUNTRY, SPECIFICATION);

    assertFalse(subdivision.isSatisfiedBy(france));
  }

  @Test
  void should_not_be_satisfied_when_country_does_not_match() {
    Location spain = Location.of(Country.of("ES"));

    Subdivision subdivision = Subdivision.of(NAME, COUNTRY);

    assertFalse(subdivision.isSatisfiedBy(spain));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    Subdivision subdivision = Subdivision.of(NAME, COUNTRY);

    assertThrows(NullPointerException.class, () -> subdivision.isSatisfiedBy(null));
  }
}
