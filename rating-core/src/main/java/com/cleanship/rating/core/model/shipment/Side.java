package com.cleanship.rating.core.model.shipment;

import com.cleanship.rating.core.exception.InvalidSideException;
import java.util.Objects;

public record Side(int millimeters) implements Comparable<Side> {
  public Side {
    if (millimeters <= 0) {
      throw InvalidSideException.of(millimeters);
    }
  }

  public static Side of(int millimeters) {
    return new Side(millimeters);
  }

  @Override
  public int compareTo(Side o) {
    Objects.requireNonNull(o);

    return Integer.compare(millimeters, o.millimeters);
  }
}
