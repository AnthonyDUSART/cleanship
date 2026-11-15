package com.cleanship.rating.domain.model.shipment;

import java.util.Objects;

public enum NoDimension implements Dimension {
  INSTANCE;

  public static NoDimension of() {
    return INSTANCE;
  }

  @Override
  public boolean isWithin(DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (minInclusive.length().compareTo(maxInclusive.length()) > 0
        || minInclusive.width().compareTo(maxInclusive.width()) > 0
        || minInclusive.height().compareTo(maxInclusive.height()) > 0) {
      throw new IllegalArgumentException("minInclusive cannot be greater than maxInclusive");
    }

    return false;
  }

  @Override
  public boolean isVolumeWithin(Volume minInclusive, Volume maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw new IllegalArgumentException("minInclusive cannot be greater than maxInclusive");
    }

    return false;
  }

  @Override
  public boolean fitsIn(DefinedDimension maxInclusive) {
    Objects.requireNonNull(maxInclusive);

    return false;
  }
}
