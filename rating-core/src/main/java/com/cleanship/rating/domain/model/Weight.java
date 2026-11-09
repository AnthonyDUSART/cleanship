package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidWeightException;

public record Weight(int grams) {
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
}
