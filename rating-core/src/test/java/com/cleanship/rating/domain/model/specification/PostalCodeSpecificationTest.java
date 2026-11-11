package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class PostalCodeSpecificationTest {
  private static final AnyPostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  @Test
  void should_and_successfully() {
    PostalCodeSpecification andSpec = ANY.and(ANY);

    AndPostalCodeSpecification expected = AndPostalCodeSpecification.of(ANY, ANY);

    assertEquals(expected, andSpec);
  }

  @Test
  void should_throw_exception_when_and_with_null() {
    assertThrows(NullPointerException.class, () -> ANY.and(null));
  }

  @Test
  void should_or_successfully() {
    PostalCodeSpecification orSpec = ANY.or(ANY);

    OrPostalCodeSpecification expected = OrPostalCodeSpecification.of(ANY, ANY);

    assertEquals(expected, orSpec);
  }

  @Test
  void should_throw_exception_when_or_with_null() {
    assertThrows(NullPointerException.class, () -> ANY.or(null));
  }

  @Test
  void should_not_successfully() {
    PostalCodeSpecification notSpec = ANY.not();

    NotPostalCodeSpecification expected = NotPostalCodeSpecification.of(ANY);

    assertEquals(expected, notSpec);
  }

  @Test
  void should_create_starts_with_specification() {
    StartsWithMatchingPostalCodeSpecification expected =
        StartsWithMatchingPostalCodeSpecification.of("75");

    assertEquals(expected, PostalCodeSpecification.startsWith("75"));
  }

  @Test
  void should_throw_exception_when_starts_with_null() {
    assertThrows(NullPointerException.class, () -> PostalCodeSpecification.startsWith(null));
  }

  @Test
  void should_create_ends_with_specification() {
    EndsWithMatchingPostalCodeSpecification expected =
        EndsWithMatchingPostalCodeSpecification.of("000");

    assertEquals(expected, PostalCodeSpecification.endsWith("000"));
  }

  @Test
  void should_throw_exception_when_ends_with_null() {
    assertThrows(NullPointerException.class, () -> PostalCodeSpecification.endsWith(null));
  }

  @Test
  void should_create_contains_specification() {
    ContainsMatchingPostalCodeSpecification expected =
        ContainsMatchingPostalCodeSpecification.of("50");

    assertEquals(expected, PostalCodeSpecification.contains("50"));
  }

  @Test
  void should_throw_exception_when_contains_null() {
    assertThrows(NullPointerException.class, () -> PostalCodeSpecification.contains(null));
  }
}
