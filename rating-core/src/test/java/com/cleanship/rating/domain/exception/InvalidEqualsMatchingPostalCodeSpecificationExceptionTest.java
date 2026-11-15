package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidEqualsMatchingPostalCodeSpecificationExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidEqualsMatchingPostalCodeSpecificationException exception =
        InvalidEqualsMatchingPostalCodeSpecificationException.of("test");

    assertEquals(
        "Equals matching postal code specification \"test\" is invalid", exception.getMessage());
  }
}
