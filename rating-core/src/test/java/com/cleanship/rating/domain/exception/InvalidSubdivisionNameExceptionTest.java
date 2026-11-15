package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidSubdivisionNameExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidSubdivisionNameException exception = InvalidSubdivisionNameException.of("Paris");

    assertEquals("Subdivision name \"Paris\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> InvalidSubdivisionNameException.of(null));
  }
}
