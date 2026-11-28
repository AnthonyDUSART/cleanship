package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidEndsWithMatchingPostalCodeSpecificationExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidEndsWithMatchingPostalCodeSpecificationException exception =
        InvalidEndsWithMatchingPostalCodeSpecificationException.of("test");

    assertEquals(
        "Ends with matching postal code specification \"test\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_suffix_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidEndsWithMatchingPostalCodeSpecificationException.of(null));
  }
}
