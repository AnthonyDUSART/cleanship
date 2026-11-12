package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cleanship.rating.domain.model.location.PostalCode;

final class NotPostalCodeSpecificationTest {
  private static final AnyPostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  @Test
  void should_store_postal_code_specification() {
    NotPostalCodeSpecification spec = NotPostalCodeSpecification.of(ANY);

    assertEquals(ANY, spec.postalCodeSpecification());
  }

  @Test
  void should_throw_exception_when_postal_code_specification_is_null() {
    assertThrows(NullPointerException.class, () -> NotPostalCodeSpecification.of(null));
  }

  @Test
  void should_not_be_satisfied_when_specification_match() {
    NotPostalCodeSpecification spec = NotPostalCodeSpecification.of(ANY);

    PostalCode postalCode = PostalCode.none();

    assertFalse(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_be_satisfied_when_specification_does_not_match() {
    NotPostalCodeSpecification spec = NotPostalCodeSpecification.of(StartsWithMatchingPostalCodeSpecification.of("75"));

    PostalCode postalCode = PostalCode.of("26000");

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    NotPostalCodeSpecification spec = NotPostalCodeSpecification.of(ANY);

    assertThrows(NullPointerException.class, () -> spec.isSatisfiedBy(null));
  }
}
