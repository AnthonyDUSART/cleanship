package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

final class NoDimensionTest {
  @Test
  void should_all_instances_are_same() {
    NoDimension a = NoDimension.of();
    NoDimension b = NoDimension.of();

    assertSame(a, b);
  }
}
