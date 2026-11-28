package com.cleanship.rating.service.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TariffIdTest {
  @Test
  void should_store_value() {
    UUID value = UUID.fromString("01a20ad1-85ff-75cb-a9fd-a9335819409e");

    assertEquals(value, TariffId.of(value).value());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> TariffId.of(null));
  }
}
