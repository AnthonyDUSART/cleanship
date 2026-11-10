package com.cleanship.rating.domain.model;

public enum NoDimension implements Dimension {
  INSTANCE;

  public static NoDimension of() {
    return INSTANCE;
  }

  @Override
  public boolean isWithin(DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    return false;
  }

  @Override
  public boolean fitsIn(DefinedDimension maxInclusive) {
    return false;
  }
}
