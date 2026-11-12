package com.cleanship.rating.domain.model.shipment;

import java.util.Objects;

enum NoDimension implements Dimension {
  INSTANCE;

  static NoDimension of() {
    return INSTANCE;
  }

  @Override
  public boolean isWithin(DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    return false;
  }

  @Override
  public boolean isVolumeWithin(Volume minInclusive, Volume maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    return false;
  }

  @Override
  public boolean fitsIn(DefinedDimension maxInclusive) {
    Objects.requireNonNull(maxInclusive);

    return false;
  }
}
