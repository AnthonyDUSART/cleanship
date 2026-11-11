package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
