package com.cleanship.rating.service.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TenantIdTest {
  @Test
  void should_store_value() {
    UUID value = UUID.fromString("01a20f4e-f627-778c-98bc-7785680e207a");

    assertEquals(value, TenantId.of(value).value());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> TenantId.of(null));
  }
}
