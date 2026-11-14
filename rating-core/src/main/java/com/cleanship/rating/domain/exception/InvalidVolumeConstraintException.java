package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.shipment.Volume;
import java.util.Objects;

public final class InvalidVolumeConstraintException extends DomainException {
  private InvalidVolumeConstraintException(Volume minInclusive, Volume maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    super(
        String.format(
            "Volume constraint minInclusive \"%d\" cannot be greater than maxInclusive \"%d\"",
            minInclusive.cubicMillimeters(), maxInclusive.cubicMillimeters()));
  }

  public static InvalidVolumeConstraintException of(Volume minInclusive, Volume maxInclusive) {
    return new InvalidVolumeConstraintException(minInclusive, maxInclusive);
  }
}
