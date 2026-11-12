package com.cleanship.rating.domain.model;

import java.util.Objects;

public record DefinedDimension(Side length, Side width, Side height) implements Dimension {
  private static final DefinedDimension MIN_POSSIBLE =
      DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

  public DefinedDimension {
    Objects.requireNonNull(length);
    Objects.requireNonNull(width);
    Objects.requireNonNull(height);

    if (length.compareTo(width) < 0) {
      Side temp = length;
      length = width;
      width = temp;
    }

    if (width.compareTo(height) < 0) {
      Side temp = width;
      width = height;
      height = temp;
    }

    if (length.compareTo(width) < 0) {
      Side temp = length;
      length = width;
      width = temp;
    }
  }

  public static DefinedDimension of(Side length, Side width, Side height) {
    return new DefinedDimension(length, width, height);
  }

  @Override
  public boolean isWithin(DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (minInclusive.length.compareTo(maxInclusive.length) > 0
        || minInclusive.width.compareTo(maxInclusive.width) > 0
        || minInclusive.height.compareTo(maxInclusive.height) > 0) {
      throw new IllegalArgumentException("minInclusive cannot be greater than maxInclusive");
    }

    return length.compareTo(minInclusive.length) >= 0
        && length.compareTo(maxInclusive.length) <= 0
        && width.compareTo(minInclusive.width) >= 0
        && width.compareTo(maxInclusive.width) <= 0
        && height.compareTo(minInclusive.height) >= 0
        && height.compareTo(maxInclusive.height) <= 0;
  }

  @Override
  public boolean fitsIn(DefinedDimension maxInclusive) {
    return isWithin(MIN_POSSIBLE, maxInclusive);
  }

  public Volume volume() {
    return Volume.of(this);
  }
}
