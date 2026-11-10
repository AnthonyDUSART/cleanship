package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class NoPostalCodeTest {
  @Test
  void should_all_instances_are_same() {
    assertSame(NoPostalCode.of(), NoPostalCode.of());
  }
}
