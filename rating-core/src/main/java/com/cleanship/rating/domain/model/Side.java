package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidSideException;

public record Side(int millimeters) {
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
}
