package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidEqualsMatchingPostalCodeSpecificationExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidEqualsMatchingPostalCodeSpecificationException exception =
        InvalidEqualsMatchingPostalCodeSpecificationException.of("test");

    assertEquals(
        "Equals matching postal code specification \"test\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidEqualsMatchingPostalCodeSpecificationException.of(null));
  }
}
