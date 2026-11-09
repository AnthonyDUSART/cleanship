package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidSideException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class SideTest {
  @Test
  void should_store_millimeters() {
    assertEquals(30_000, Side.of(30_000).millimeters());
  }

  @ParameterizedTest
  @ValueSource(ints = {-1000, -1, 0})
  void should_throw_exception_when_side_is_invalid(int millimeters) {
    assertThrows(InvalidSideException.class, () -> Side.of(millimeters));
  }
}
