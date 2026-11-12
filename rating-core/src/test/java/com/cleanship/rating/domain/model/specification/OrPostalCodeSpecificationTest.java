package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.location.PostalCode;
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

  @Test
  void should_be_satisfied_when_left_does_not_match() {
    OrPostalCodeSpecification spec = OrPostalCodeSpecification.of(STARTS_WITH_26, STARTS_WITH_75);

    PostalCode postalCode = PostalCode.of("75000");

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_be_satisfied_when_right_does_not_match() {
    OrPostalCodeSpecification spec = OrPostalCodeSpecification.of(STARTS_WITH_26, STARTS_WITH_75);

    PostalCode postalCode = PostalCode.of("26000");

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied_when_both_specification_does_not_match() {
    OrPostalCodeSpecification spec = OrPostalCodeSpecification.of(STARTS_WITH_26, STARTS_WITH_75);

    PostalCode postalCode = PostalCode.of("41000");

    assertFalse(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    OrPostalCodeSpecification spec = OrPostalCodeSpecification.of(STARTS_WITH_26, ANY);

    assertThrows(NullPointerException.class, () -> spec.isSatisfiedBy(null));
  }
}
