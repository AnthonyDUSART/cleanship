package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.shipment.DefinedDimension;

public record DimensionConstraint(DefinedDimension minInclusive, DefinedDimension maxInclusive) {
  public static DimensionConstraint of(
      DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    return new DimensionConstraint(minInclusive, maxInclusive);
  }
}
