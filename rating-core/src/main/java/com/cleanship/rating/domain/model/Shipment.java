package com.cleanship.rating.domain.model;

public record Shipment(Location origin, Location destination, Parcel parcel) {
  public static Shipment of(Location origin, Location destination, Parcel parcel) {
    return new Shipment(origin, destination, parcel);
  }
}
