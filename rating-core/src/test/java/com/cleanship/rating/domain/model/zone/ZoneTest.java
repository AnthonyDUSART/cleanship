package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.Country;
import com.cleanship.rating.domain.model.specification.PostalCodeSpecification;
import java.util.Set;
import org.junit.jupiter.api.Test;

final class ZoneTest {
  private static final ZoneName NAME = ZoneName.of("France");

  private static final Set<Subdivision> SUBDIVISIONS =
      Set.of(
          Subdivision.of(
              SubdivisionName.of("Paris"),
              Country.of("FR"),
              PostalCodeSpecification.isEqualTo("75000")));

  @Test
  void should_store_name() {
    Zone zone = Zone.of(NAME, SUBDIVISIONS);

    assertEquals(NAME, zone.name());
  }
}
