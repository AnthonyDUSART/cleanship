package com.cleanship.rating.domain.model;

import java.util.Objects;

public record Shipment(Location origin, Location destination, Parcel parcel, Money declaredValue) {
  public Shipment {
    Objects.requireNonNull(origin);
    Objects.requireNonNull(destination);
    Objects.requireNonNull(parcel);
    Objects.requireNonNull(declaredValue);
  }

  public static Shipment of(
      Location origin, Location destination, Parcel parcel, Money declaredValue) {
    return new Shipment(origin, destination, parcel, declaredValue);
  }
}
