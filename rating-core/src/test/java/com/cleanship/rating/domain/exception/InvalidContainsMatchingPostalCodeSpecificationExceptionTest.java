package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidContainsMatchingPostalCodeSpecificationExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidContainsMatchingPostalCodeSpecificationException exception =
        InvalidContainsMatchingPostalCodeSpecificationException.of("test");

    assertEquals(
        "Contains matching postal code specification \"test\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_suffix_is_invalid() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidContainsMatchingPostalCodeSpecificationException.of(null));
  }
}
