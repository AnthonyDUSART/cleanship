package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidWeightException;
import java.util.Objects;

public record Weight(int grams) implements Comparable<Weight> {
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

  public boolean isWithin(Weight minInclusive, Weight maxInclusive) {
    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw new IllegalArgumentException(
          String.format(
              "minInclusive \"%d\" cannot be greater than maxInclusive \"%d\"",
              minInclusive.grams, maxInclusive.grams));
    }
    return compareTo(minInclusive) >= 0 && compareTo(maxInclusive) <= 0;
  }
}
