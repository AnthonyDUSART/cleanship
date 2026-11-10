package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class ParcelTest {
  @Test
  void should_store_weight() {
    Parcel parcel = Parcel.of(Weight.of(1));

    assertEquals(Weight.of(1), parcel.weight());
  }

  @Test
  void should_store_no_dimension() {
    Parcel parcel = Parcel.of(Weight.of(1));

    assertEquals(NoDimension.of(), parcel.dimension());
  }

  @Test
  void should_store_defined_dimension() {
    Side side = Side.of(1);
    Parcel parcel = Parcel.of(Weight.of(1), DefinedDimension.of(side, side, side));

    assertEquals(DefinedDimension.of(side, side, side), parcel.dimension());
  }
}
