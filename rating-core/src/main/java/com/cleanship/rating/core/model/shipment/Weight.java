package com.cleanship.rating.core.model.shipment;

import com.cleanship.rating.core.exception.InvalidWeightException;
import java.util.Objects;

public record Weight(int grams) implements Comparable<Weight> {
  public Weight {
    if (grams <= 0) {
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
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw new IllegalArgumentException(
          String.format(
              "minInclusive cannot be greater than maxInclusive",
              minInclusive.grams,
              maxInclusive.grams));
    }
    return compareTo(minInclusive) >= 0 && compareTo(maxInclusive) <= 0;
  }
}
