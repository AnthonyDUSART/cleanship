package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
