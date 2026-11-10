package com.cleanship.rating.domain.model;

public record Parcel(Weight weight, Dimension dimension) {
  public static Parcel of(Weight weight) {
    return new Parcel(weight, NoDimension.of());
  }

  public static Parcel of(Weight weight, DefinedDimension dimension) {
    return new Parcel(weight, dimension);
  }
}
