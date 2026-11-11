package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class ShipmentTest {
  private static final Location ORIGIN = Location.of(Country.of("FR"));

  private static final Location DESTINATION = Location.of(Country.of("ES"), PostalCode.of("28001"));

  private static final Parcel PARCEL = Parcel.of(Weight.of(1000));

  @Test
  void should_store_origin() {
    Shipment shipment = Shipment.of(ORIGIN, DESTINATION, PARCEL);

    assertEquals(ORIGIN, shipment.origin());
  }

  @Test
  void should_store_destination() {
    Shipment shipment = Shipment.of(ORIGIN, DESTINATION, PARCEL);

    assertEquals(DESTINATION, shipment.destination());
  }

  @Test
  void should_store_parcel() {
    Shipment shipment = Shipment.of(ORIGIN, DESTINATION, PARCEL);

    assertEquals(PARCEL, shipment.parcel());
  }

  @Test
  void should_throw_exception_when_origin_is_null() {
    assertThrows(NullPointerException.class, () -> Shipment.of(null, DESTINATION, PARCEL));
  }
}
