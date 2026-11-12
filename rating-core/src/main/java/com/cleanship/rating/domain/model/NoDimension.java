package com.cleanship.rating.domain.model;

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

    return false;
  }

  @Override
  public boolean isVolumeWithin(Volume minInclusive, Volume maxInclusive) {
    Objects.requireNonNull(minInclusive);

    return false;
  }

  @Override
  public boolean fitsIn(DefinedDimension maxInclusive) {
    Objects.requireNonNull(maxInclusive);

    return false;
  }
}
