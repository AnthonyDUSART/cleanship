package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidVolumeException;
import java.util.Objects;

public record Volume(long cubicMillimeters) implements Comparable<Volume> {
  public Volume {
    if (0 >= cubicMillimeters) {
      throw InvalidVolumeException.of(cubicMillimeters);
    }
  }

  public static Volume of(long cubicMillimeters) {
    return new Volume(cubicMillimeters);
  }

  public static Volume of(DefinedDimension dimension) {
    return new Volume(
        (long) dimension.length().millimeters()
            * dimension.width().millimeters()
            * dimension.height().millimeters());
  }

  @Override
  public int compareTo(Volume o) {
    return Long.compare(cubicMillimeters, o.cubicMillimeters);
  }

  public boolean isWithin(Volume minInclusive, Volume maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw new IllegalArgumentException("minInclusive cannot be greater than maxInclusive");
    }

    return compareTo(minInclusive) >= 0 && compareTo(maxInclusive) <= 0;
  }
}
