package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class OrPostalCodeSpecificationTest {
  private static final AnyPostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  private static final StartsWithMatchingPostalCodeSpecification STARTS_WITH_75 =
      StartsWithMatchingPostalCodeSpecification.of("75");

  private static final StartsWithMatchingPostalCodeSpecification STARTS_WITH_26 =
      StartsWithMatchingPostalCodeSpecification.of("26");

  @Test
  void should_store_left() {
    OrPostalCodeSpecification spec = OrPostalCodeSpecification.of(ANY, STARTS_WITH_26);

    assertEquals(ANY, spec.left());
  }

  @Test
  void should_store_right() {
    OrPostalCodeSpecification spec = OrPostalCodeSpecification.of(ANY, STARTS_WITH_26);

    assertEquals(STARTS_WITH_26, spec.right());
  }

  @Test
  void should_throw_exception_when_left_is_null() {
    assertThrows(NullPointerException.class, () -> OrPostalCodeSpecification.of(null, ANY));
  }

  @Test
  void should_throw_exception_when_right_is_null() {
    assertThrows(NullPointerException.class, () -> OrPostalCodeSpecification.of(ANY, null));
  }
}
