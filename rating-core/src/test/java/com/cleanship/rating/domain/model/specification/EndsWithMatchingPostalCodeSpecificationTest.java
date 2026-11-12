package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.location.PostalCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

  @ParameterizedTest
  @CsvSource({"5000, 75000", "760, 26760", "1, 75001"})
  void should_be_satisfied(String suffix, String postalCodeValue) {
    EndsWithMatchingPostalCodeSpecification spec =
        EndsWithMatchingPostalCodeSpecification.of(suffix);

    PostalCode postalCode = PostalCode.of(postalCodeValue);

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied() {
    EndsWithMatchingPostalCodeSpecification spec =
        EndsWithMatchingPostalCodeSpecification.of("000");

    PostalCode postalCode = PostalCode.of("26760");

    assertFalse(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied_when_no_postal_code() {
    EndsWithMatchingPostalCodeSpecification spec =
        EndsWithMatchingPostalCodeSpecification.of("000");

    assertFalse(spec.isSatisfiedBy(PostalCode.none()));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    EndsWithMatchingPostalCodeSpecification spec =
        EndsWithMatchingPostalCodeSpecification.of("000");

    assertThrows(NullPointerException.class, () -> spec.isSatisfiedBy(null));
  }
}
