package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidPercentageException;

public record Percentage(int valueInMilliPercent) {
  public Percentage {
    if (valueInMilliPercent < 0) {
      throw InvalidPercentageException.of(valueInMilliPercent);
    }
  }

  public static Percentage of(int valueInMilliPercent) {
    return new Percentage(valueInMilliPercent);
  }
}
