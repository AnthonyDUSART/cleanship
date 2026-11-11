package com.cleanship.rating.domain.model;

import java.util.Objects;

public record Shipment(Location origin, Location destination, Parcel parcel) {
  public Shipment {
    Objects.requireNonNull(origin);
    Objects.requireNonNull(destination);
    Objects.requireNonNull(parcel);
  }

  public static Shipment of(Location origin, Location destination, Parcel parcel) {
    return new Shipment(origin, destination, parcel);
  }
}
