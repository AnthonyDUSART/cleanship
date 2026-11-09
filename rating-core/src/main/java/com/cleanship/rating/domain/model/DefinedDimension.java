package com.cleanship.rating.domain.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public record DefinedDimension(Side length, Side width, Side height) implements Dimension {
  public DefinedDimension {
    Objects.requireNonNull(length);
    Objects.requireNonNull(width);
    Objects.requireNonNull(height);
  }

  public static DefinedDimension of(Side length, Side width, Side height) {
    return new DefinedDimension(length, width, height);
  }

  private List<Side> sortedSides() {
    return Stream.of(length, width, height).sorted(Comparator.reverseOrder()).toList();
  }

  @Override
  public boolean isWithin(DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    List<Side> currentSides = this.sortedSides();

    List<Side> minSides = minInclusive.sortedSides();

    List<Side> maxSides = maxInclusive.sortedSides();

    for (int i = 0; i < 3; i++) {
      if (minSides.get(i).compareTo(maxSides.get(i)) > 0) {
        throw new IllegalArgumentException("minInclusive cannot be greater than maxInclusive");
      }

      if (currentSides.get(i).compareTo(minSides.get(i)) < 0
          || currentSides.get(i).compareTo(maxSides.get(i)) > 0) {
        return false;
      }
    }
    return true;
  }
}
