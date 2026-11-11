package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class StartsWithMatchingPostalCodeSpecificationTest {
  @Test
  void should_store_prefix() {
    assertEquals("75", StartsWithMatchingPostalCodeSpecification.of("75").prefix());
  }

  @Test
  void should_throw_exception_when_prefix_is_null() {
    assertThrows(
        NullPointerException.class, () -> StartsWithMatchingPostalCodeSpecification.of(null));
  }
}
