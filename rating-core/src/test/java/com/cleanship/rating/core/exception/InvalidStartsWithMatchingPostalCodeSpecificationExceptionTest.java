package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidStartsWithMatchingPostalCodeSpecificationExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidStartsWithMatchingPostalCodeSpecificationException exception =
        InvalidStartsWithMatchingPostalCodeSpecificationException.of("");

    assertEquals(
        "Starts with matching postal code specification \"\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_prefix_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidStartsWithMatchingPostalCodeSpecificationException.of(null));
  }
}
