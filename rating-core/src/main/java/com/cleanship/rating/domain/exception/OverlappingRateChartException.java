package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.zone.Zone;

public final class OverlappingRateChartException extends DomainException {
  private OverlappingRateChartException(Zone zone) {
    super(
        String.format(
            "Rate chart for zone \"%s\" contains overlapping rates", zone.name().value()));
  }

  public static OverlappingRateChartException of(Zone zone) {
    return new OverlappingRateChartException(zone);
  }
}
