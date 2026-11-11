package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class EndsWithMatchingPostalCodeSpecificationTest {
  @Test
  void should_store_suffix() {
    EndsWithMatchingPostalCodeSpecification spec =
        EndsWithMatchingPostalCodeSpecification.of("000");

    assertEquals("000", spec.suffix());
  }

  @Test
  void should_throw_exception_when_suffix_is_null() {
    assertThrows(
        NullPointerException.class, () -> EndsWithMatchingPostalCodeSpecification.of(null));
  }
}
