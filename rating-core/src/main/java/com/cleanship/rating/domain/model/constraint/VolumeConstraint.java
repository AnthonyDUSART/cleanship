package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.shipment.Volume;

public record VolumeConstraint(Volume minInclusive, Volume maxInclusive) {
  public static VolumeConstraint of(Volume minInclusive, Volume maxInclusive) {
    return new VolumeConstraint(minInclusive, maxInclusive);
  }
}
