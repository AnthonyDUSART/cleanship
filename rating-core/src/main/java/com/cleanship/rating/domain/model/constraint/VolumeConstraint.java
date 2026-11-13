package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.shipment.Volume;
import java.util.Objects;

public record VolumeConstraint(Volume minInclusive, Volume maxInclusive) {
  public VolumeConstraint {
    Objects.requireNonNull(minInclusive);
  }

  public static VolumeConstraint of(Volume minInclusive, Volume maxInclusive) {
    return new VolumeConstraint(minInclusive, maxInclusive);
  }
}
