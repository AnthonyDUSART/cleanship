package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.PostalCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class ContainsPostalCodeSpecificationTest {
  @Test
  void should_store_sequence() {
    ContainsPostalCodeSpecification spec = ContainsPostalCodeSpecification.of("50");

    assertEquals("50", spec.sequence());
  }

  @Test
  void should_throw_exception_when_sequence_is_null() {
    assertThrows(NullPointerException.class, () -> ContainsPostalCodeSpecification.of(null));
  }

  @ParameterizedTest
  @CsvSource({"50, 75000", "760, 26760", "1, 75001"})
  void should_be_satisfied(String sequence, String postalCodeValue) {
    ContainsPostalCodeSpecification spec = ContainsPostalCodeSpecification.of(sequence);

    PostalCode postalCode = PostalCode.of(postalCodeValue);

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied() {
    ContainsPostalCodeSpecification spec = ContainsPostalCodeSpecification.of("76");

    PostalCode postalCode = PostalCode.of("75000");

    assertFalse(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    ContainsPostalCodeSpecification spec = ContainsPostalCodeSpecification.of("50");

    assertThrows(NullPointerException.class, () -> spec.isSatisfiedBy(null));
  }

  @Test
  void should_not_be_satisfied_when_no_postal_code() {
    ContainsPostalCodeSpecification spec = ContainsPostalCodeSpecification.of("50");

    assertFalse(spec.isSatisfiedBy(PostalCode.none()));
  }
}
