package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.location.PostalCode;
import org.junit.jupiter.api.Test;

final class AndPostalCodeSpecificationTest {
  private static final PostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  private static final PostalCodeSpecification STARTS_WITH_75 =
      StartsWithMatchingPostalCodeSpecification.of("75");

  private static final PostalCodeSpecification ENDS_WITH_00 =
      EndsWithMatchingPostalCodeSpecification.of("00");

  @Test
  void should_store_left() {
    AndPostalCodeSpecification spec = AndPostalCodeSpecification.of(ANY, ANY);

    assertEquals(ANY, spec.left());
  }

  @Test
  void should_store_right() {
    AndPostalCodeSpecification spec = AndPostalCodeSpecification.of(ANY, ANY);

    assertEquals(ANY, spec.right());
  }

  @Test
  void should_throw_exception_when_left_is_null() {
    assertThrows(NullPointerException.class, () -> AndPostalCodeSpecification.of(null, ANY));
  }

  @Test
  void should_throw_exception_when_right_is_null() {
    assertThrows(NullPointerException.class, () -> AndPostalCodeSpecification.of(ANY, null));
  }

  @Test
  void should_be_satisfied_when_both_specifications_match() {
    AndPostalCodeSpecification spec = AndPostalCodeSpecification.of(STARTS_WITH_75, ENDS_WITH_00);

    assertTrue(spec.isSatisfiedBy(PostalCode.of("75000")));
  }

  @Test
  void should_not_be_satisfied_when_right_does_not_match() {
    AndPostalCodeSpecification spec = AndPostalCodeSpecification.of(STARTS_WITH_75, ENDS_WITH_00);

    assertFalse(spec.isSatisfiedBy(PostalCode.of("75001")));
  }

  @Test
  void should_not_be_satisfied_when_left_does_not_match() {
    AndPostalCodeSpecification spec = AndPostalCodeSpecification.of(STARTS_WITH_75, ENDS_WITH_00);

    assertFalse(spec.isSatisfiedBy(PostalCode.of("69000")));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    AndPostalCodeSpecification spec = AndPostalCodeSpecification.of(ANY, ANY);

    assertThrows(NullPointerException.class, () -> spec.isSatisfiedBy(null));
  }
}
