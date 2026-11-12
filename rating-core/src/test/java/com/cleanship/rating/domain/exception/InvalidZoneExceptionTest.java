package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.zone.ZoneName;
import org.junit.jupiter.api.Test;

final class InvalidZoneExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidZoneException exception = InvalidZoneException.of(ZoneName.of("France"));

    assertEquals("Zone \"France\" must contain at least one subdivision", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_name_is_null() {
    assertThrows(NullPointerException.class, () -> InvalidZoneException.of(null));
  }
}
