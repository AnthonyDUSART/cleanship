package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.PostalCode;
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
}
