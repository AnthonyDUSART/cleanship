package com.cleanship.rating.domain.model.shipment;

import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Money;
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
