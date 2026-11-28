package com.cleanship.rating.core.exception;

import com.cleanship.rating.core.model.zone.Zone;
import java.util.Objects;

public final class OverlappingRateChartException extends DomainException {
  private OverlappingRateChartException(Zone zone) {
    Objects.requireNonNull(zone);

    super(
        String.format(
            "Rate chart for zone \"%s\" contains overlapping rates", zone.name().value()));
  }

  public static OverlappingRateChartException of(Zone zone) {
    return new OverlappingRateChartException(zone);
  }
}
