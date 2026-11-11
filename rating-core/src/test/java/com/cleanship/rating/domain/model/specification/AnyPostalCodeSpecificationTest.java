package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.PostalCode;
import org.junit.jupiter.api.Test;

final class AnyPostalCodeSpecificationTest {
  @Test
  void should_all_instances_are_same() {
    assertSame(AnyPostalCodeSpecification.of(), AnyPostalCodeSpecification.of());
  }

  @Test
  void should_be_satisfied() {
    assertTrue(AnyPostalCodeSpecification.of().isSatisfiedBy(PostalCode.none()));
  }
}
