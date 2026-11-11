package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.PostalCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class StartsWithMatchingPostalCodeSpecificationTest {
  @Test
  void should_store_prefix() {
    assertEquals("75", StartsWithMatchingPostalCodeSpecification.of("75").prefix());
  }

  @Test
  void should_throw_exception_when_prefix_is_null() {
    assertThrows(
        NullPointerException.class, () -> StartsWithMatchingPostalCodeSpecification.of(null));
  }

  @ParameterizedTest
  @CsvSource({"75, 75001", "75, 75020", "20, 20000"})
  void should_be_satisfied(String prefix, String postalCodeValue) {
    StartsWithMatchingPostalCodeSpecification spec =
        StartsWithMatchingPostalCodeSpecification.of(prefix);

    PostalCode postalCode = PostalCode.of(postalCodeValue);

    assertTrue(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied() {
    StartsWithMatchingPostalCodeSpecification spec =
        StartsWithMatchingPostalCodeSpecification.of("75");

    PostalCode postalCode = PostalCode.of("76000");

    assertFalse(spec.isSatisfiedBy(postalCode));
  }

  @Test
  void should_not_be_satisfied_when_no_postal_code() {
    StartsWithMatchingPostalCodeSpecification spec =
        StartsWithMatchingPostalCodeSpecification.of("75");

    assertFalse(spec.isSatisfiedBy(PostalCode.none()));
  }
}
