package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class ParcelTest {
  @Test
  void should_store_weight() {
    Parcel parcel = Parcel.of(Weight.of(1));

    assertEquals(Weight.of(1), parcel.weight());
  }
}
