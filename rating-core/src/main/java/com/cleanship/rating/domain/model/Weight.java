package com.cleanship.rating.domain.model;

import java.util.Objects;

import com.cleanship.rating.domain.exception.InvalidWeightException;

public record Weight(int grams) implements Comparable<Weight> {
  /**
   * @throws InvalidWeightException
   */
  public Weight {
    if (0 >= grams) {
      throw InvalidWeightException.of(grams);
    }
  }

  public static Weight of(int grams) {
    return new Weight(grams);
  }

  @Override
  public int compareTo(Weight o) {
    Objects.requireNonNull(o);

    return Integer.compare(grams, o.grams);
  }
}
