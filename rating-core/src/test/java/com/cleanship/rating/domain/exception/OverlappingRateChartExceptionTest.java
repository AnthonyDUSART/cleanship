package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
import java.util.Set;
import org.junit.jupiter.api.Test;

final class OverlappingRateChartExceptionTest {
  @Test
  void should_have_valid_message() {
    Zone zone =
        Zone.of(
            ZoneName.of("France"),
            Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR"))));

    OverlappingRateChartException exception = OverlappingRateChartException.of(zone);
    assertEquals(
        "Rate chart for zone \"France\" contains overlapping rates", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_zone_is_null() {
    assertThrows(NullPointerException.class, () -> OverlappingRateChartException.of(null));
  }
}
