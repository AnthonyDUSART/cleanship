package com.cleanship.rating.domain.model;

public record DefinedDimension(Side length, Side width, Side height) implements Dimension {
  public static DefinedDimension of(Side length, Side width, Side height) {
    return new DefinedDimension(length, width, height);
  }

  @Override
  public boolean isWithin(DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    // TODO Auto-generated method stub
    return false;
  }
}
