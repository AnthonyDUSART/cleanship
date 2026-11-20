package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.zone.Zone;

public final class EmptyRateChartException extends DomainException {
  private EmptyRateChartException(Zone zone) {
    super(
        String.format(
            "Rate chart for zone \"%s\" must contain at least one rate", zone.name().value()));
  }

  public static EmptyRateChartException of(Zone zone) {
    return new EmptyRateChartException(zone);
  }
}
