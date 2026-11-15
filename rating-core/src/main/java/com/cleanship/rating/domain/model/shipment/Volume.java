package com.cleanship.rating.domain.model.shipment;

import com.cleanship.rating.domain.exception.InvalidVolumeException;
import java.util.Objects;

public record Volume(long cubicMillimeters) implements Comparable<Volume> {
  public Volume {
    if (cubicMillimeters <= 0) {
      throw InvalidVolumeException.of(cubicMillimeters);
    }
  }

  public static Volume of(long cubicMillimeters) {
    return new Volume(cubicMillimeters);
  }

  public static Volume of(DefinedDimension dimension) {
    Objects.requireNonNull(dimension);

    long surface = (long) dimension.length().millimeters() * dimension.width().millimeters();
    long cubicMillimeters = Math.multiplyExact(surface, dimension.height().millimeters());

    return new Volume(cubicMillimeters);
  }

  @Override
  public int compareTo(Volume o) {
    Objects.requireNonNull(o);

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
