package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.PostalCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class ContainsMatchingPostalCodeSpecificationTest {
  @Test
  void should_store_sequence() {
    ContainsMatchingPostalCodeSpecification spec = ContainsMatchingPostalCodeSpecification.of("50");

    assertEquals("50", spec.sequence());
  }

  @Test
  void should_throw_exception_when_sequence_is_null() {
    assertThrows(
        NullPointerException.class, () -> ContainsMatchingPostalCodeSpecification.of(null));
  }

  @ParameterizedTest
  @CsvSource({"50, 75000", "760, 26760", "1, 75001"})
  void should_be_satisfied(String sequence, String postalCodeValue) {
    ContainsMatchingPostalCodeSpecification spec =
        ContainsMatchingPostalCodeSpecification.of(sequence);

    PostalCode postalCode = PostalCode.of(postalCodeValue);

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied() {
    ContainsMatchingPostalCodeSpecification spec = ContainsMatchingPostalCodeSpecification.of("76");

    PostalCode postalCode = PostalCode.of("75000");

    assertFalse(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    ContainsMatchingPostalCodeSpecification spec = ContainsMatchingPostalCodeSpecification.of("50");

    assertThrows(NullPointerException.class, () -> spec.isSatisfiedBy(null));
  }

  @Test
  void should_not_be_satisfied_when_no_postal_code() {
    ContainsMatchingPostalCodeSpecification spec = ContainsMatchingPostalCodeSpecification.of("50");

    assertFalse(spec.isSatisfiedBy(PostalCode.none()));
  }
}
