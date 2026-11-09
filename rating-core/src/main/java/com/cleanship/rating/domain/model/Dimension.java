package com.cleanship.rating.domain.model;

public interface Dimension {
  public static Dimension of(Side length, Side width, Side height) {
    return DefinedDimension.of(length, width, height);
  }

  public static Dimension none() {
    return NoDimension.of();
  }
}
