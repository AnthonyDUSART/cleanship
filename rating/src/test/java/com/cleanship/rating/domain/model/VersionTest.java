package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidVersionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class VersionTest {
  @Test
  void should_store_value() {
    assertEquals(3, Version.of(3).value());
  }

  @ParameterizedTest
  @ValueSource(ints = {-10, -1, 0})
  void should_throw_exception_when_version_is_invalid(int value) {
    assertThrows(InvalidVersionException.class, () -> Version.of(value));
  }

  @Test
  void should_initial_successfully() {
    assertEquals(1, Version.initial().value());
  }

  @Test
  void should_next_successfully() {
    Version version = Version.initial();

    Version expected = Version.of(3);

    assertEquals(expected, version.next().next());
  }
}
