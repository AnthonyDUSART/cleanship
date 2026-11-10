package com.cleanship.rating.domain.model;

public record Parcel(Weight weight) {
  public static Parcel of(Weight weight) {
    return new Parcel(weight);
  }
}
