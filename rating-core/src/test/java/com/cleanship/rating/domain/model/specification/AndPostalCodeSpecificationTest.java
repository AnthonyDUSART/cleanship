package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class AndPostalCodeSpecificationTest {
  private static final PostalCodeSpecification ANY_POSTAL_CODE_SPECIFICATION =
      AnyPostalCodeSpecification.of();

  @Test
  void should_store_left() {
    AndPostalCodeSpecification spec =
        AndPostalCodeSpecification.of(ANY_POSTAL_CODE_SPECIFICATION, ANY_POSTAL_CODE_SPECIFICATION);

    assertEquals(ANY_POSTAL_CODE_SPECIFICATION, spec.left());
  }

  @Test
  void should_store_right() {
    AndPostalCodeSpecification spec =
        AndPostalCodeSpecification.of(ANY_POSTAL_CODE_SPECIFICATION, ANY_POSTAL_CODE_SPECIFICATION);

    assertEquals(ANY_POSTAL_CODE_SPECIFICATION, spec.right());
  }
}
