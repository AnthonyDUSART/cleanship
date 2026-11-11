package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidSubdivisionNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class SubdivisionNameTest {
  @Test
  void should_store_value() {
    assertEquals("Paris", SubdivisionName.of("  Paris ").value());
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "",
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget"
            + " dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,"
            + " nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium"
            + " quis,."
      })
  void should_throw_exception_when_subdivision_name_is_invalid(String value) {
    assertThrows(InvalidSubdivisionNameException.class, () -> SubdivisionName.of(value));
  }
}
