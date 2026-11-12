package com.cleanship.rating.domain.model;

public sealed interface Dimension permits DefinedDimension, NoDimension {
  public static Dimension of(Side length, Side width, Side height) {
    return DefinedDimension.of(length, width, height);
  }

  public static Dimension none() {
    return NoDimension.of();
  }

  public boolean isWithin(DefinedDimension minInclusive, DefinedDimension maxInclusive);

  public boolean isVolumeWithin(Volume minInclusive, Volume maxInclusive);

  public boolean fitsIn(DefinedDimension maxInclusive);
}
