package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cleanship.rating.domain.model.location.PostalCode;

final class AnyPostalCodeSpecificationTest {
  @Test
  void should_all_instances_are_same() {
    assertSame(AnyPostalCodeSpecification.of(), AnyPostalCodeSpecification.of());
  }

  @Test
  void should_be_satisfied() {
    assertTrue(AnyPostalCodeSpecification.of().isSatisfiedBy(PostalCode.none()));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    assertThrows(
        NullPointerException.class, () -> AnyPostalCodeSpecification.of().isSatisfiedBy(null));
  }
}
