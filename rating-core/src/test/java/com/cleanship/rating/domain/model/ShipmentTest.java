package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class ShipmentTest {
  private static final Location ORIGIN = Location.of(Country.of("FR"));

  private static final Location DESTINATION = Location.of(Country.of("ES"), PostalCode.of("28001"));

  private static final Parcel PARCEL = Parcel.of(Weight.of(1000));

  private static final Money DECLARED_VALUE = Money.of(50_00, Currency.of("EUR"));

  @Test
  void should_store_origin() {
    Shipment shipment = Shipment.of(ORIGIN, DESTINATION, PARCEL, DECLARED_VALUE);

    assertEquals(ORIGIN, shipment.origin());
  }

  @Test
  void should_store_destination() {
    Shipment shipment = Shipment.of(ORIGIN, DESTINATION, PARCEL, DECLARED_VALUE);

    assertEquals(DESTINATION, shipment.destination());
  }

  @Test
  void should_store_parcel() {
    Shipment shipment = Shipment.of(ORIGIN, DESTINATION, PARCEL, DECLARED_VALUE);

    assertEquals(PARCEL, shipment.parcel());
  }

  @Test
  void should_store_declared_value() {
    Shipment shipment = Shipment.of(ORIGIN, DESTINATION, PARCEL, DECLARED_VALUE);

    assertEquals(DECLARED_VALUE, shipment.declaredValue());
  }

  @Test
  void should_throw_exception_when_origin_is_null() {
    assertThrows(
        NullPointerException.class, () -> Shipment.of(null, DESTINATION, PARCEL, DECLARED_VALUE));
  }

  @Test
  void should_throw_exception_when_destination_is_null() {
    assertThrows(
        NullPointerException.class, () -> Shipment.of(ORIGIN, null, PARCEL, DECLARED_VALUE));
  }

  @Test
  void should_throw_exception_when_parcel_is_null() {
    assertThrows(
        NullPointerException.class, () -> Shipment.of(ORIGIN, DESTINATION, null, DECLARED_VALUE));
  }

  @Test
  void should_throw_exception_when_delcared_value_is_null() {
    assertThrows(NullPointerException.class, () -> Shipment.of(ORIGIN, DESTINATION, PARCEL, null));
  }
}
