package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.InvalidZoneException;
import com.cleanship.rating.domain.model.Country;
import com.cleanship.rating.domain.model.Location;
import com.cleanship.rating.domain.model.PostalCode;
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

  @Test
  void should_store_subdivisions() {
    Zone zone = Zone.of(NAME, SUBDIVISIONS);

    assertEquals(SUBDIVISIONS, zone.subdivisions());
  }

  @Test
  void should_throw_exception_when_name_is_null() {
    assertThrows(NullPointerException.class, () -> Zone.of(null, SUBDIVISIONS));
  }

  @Test
  void should_throw_exception_when_subdivisions_is_null() {
    assertThrows(NullPointerException.class, () -> Zone.of(NAME, null));
  }

  @Test
  void should_throw_exception_when_subdivisions_is_empty() {
    assertThrows(InvalidZoneException.class, () -> Zone.of(NAME, Set.of()));
  }

  @Test
  void should_be_satisfied() {
    Location paris = Location.of(Country.of("FR"), PostalCode.of("75000"));

    Zone zone = Zone.of(NAME, SUBDIVISIONS);

    assertTrue(zone.isSatisfiedBy(paris));
  }
}
