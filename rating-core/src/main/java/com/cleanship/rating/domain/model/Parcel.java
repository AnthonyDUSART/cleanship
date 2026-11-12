package com.cleanship.rating.domain.model;

import java.util.Objects;

public record Parcel(Weight weight, Dimension dimension) {
  public Parcel {
    Objects.requireNonNull(weight);
    Objects.requireNonNull(dimension);
  }

  public static Parcel of(Weight weight) {
    return new Parcel(weight, Dimension.none());
  }

  public static Parcel of(Weight weight, Dimension dimension) {
    return new Parcel(weight, dimension);
  }
}
