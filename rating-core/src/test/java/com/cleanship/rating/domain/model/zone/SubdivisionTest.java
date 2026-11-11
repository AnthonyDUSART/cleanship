package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.Country;
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
}
