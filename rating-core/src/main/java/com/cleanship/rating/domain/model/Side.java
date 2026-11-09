package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidSideException;

public record Side(int millimeters) implements Comparable<Side> {
  /**
   * @throws InvalidSideException
   */
  public Side {
    if (0 >= millimeters) {
      throw InvalidSideException.of(millimeters);
    }
  }

  /**
   * @throws InvalidSideException
   */
  public static Side of(int millimeters) {
    return new Side(millimeters);
  }

  @Override
  public int compareTo(Side o) {
    return Integer.compare(millimeters, o.millimeters);
  }
}
