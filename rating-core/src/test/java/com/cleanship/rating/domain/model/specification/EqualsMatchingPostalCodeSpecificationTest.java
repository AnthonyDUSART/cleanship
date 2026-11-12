package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.location.PostalCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class EqualsMatchingPostalCodeSpecificationTest {
  @Test
  void should_store_value() {
    EqualsMatchingPostalCodeSpecification spec = EqualsMatchingPostalCodeSpecification.of("75000");

    assertEquals("75000", spec.value());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> EqualsMatchingPostalCodeSpecification.of(null));
  }

  @ParameterizedTest
  @CsvSource({"75000, 75000", "26000, 26000", "26760, 26760"})
  void should_be_satisfied(String value, String postalCodeValue) {
    EqualsMatchingPostalCodeSpecification spec = EqualsMatchingPostalCodeSpecification.of(value);

    PostalCode postalCode = PostalCode.of(postalCodeValue);

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied() {
    EqualsMatchingPostalCodeSpecification spec = EqualsMatchingPostalCodeSpecification.of("75000");

    PostalCode postalCode = PostalCode.of("26000");

    assertFalse(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied_when_no_postal_code() {
    EqualsMatchingPostalCodeSpecification spec = EqualsMatchingPostalCodeSpecification.of("75000");

    assertFalse(spec.isSatisfiedBy(PostalCode.none()));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null() {
    EqualsMatchingPostalCodeSpecification spec = EqualsMatchingPostalCodeSpecification.of("75000");

    assertThrows(NullPointerException.class, () -> spec.isSatisfiedBy(null));
  }
}
