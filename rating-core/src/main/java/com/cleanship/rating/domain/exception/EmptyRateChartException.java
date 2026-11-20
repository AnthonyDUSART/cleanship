package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.zone.Zone;
import java.util.Objects;

public final class EmptyRateChartException extends DomainException {
  private EmptyRateChartException(Zone zone) {
    Objects.requireNonNull(zone);

    super(
        String.format(
            "Rate chart for zone \"%s\" must contain at least one rate", zone.name().value()));
  }

  public static EmptyRateChartException of(Zone zone) {
    return new EmptyRateChartException(zone);
  }
}
