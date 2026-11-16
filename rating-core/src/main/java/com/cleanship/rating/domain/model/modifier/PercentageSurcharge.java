package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Percentage;

public record PercentageSurcharge(Percentage percentage) {
  public static PercentageSurcharge of(Percentage percentage) {
    return new PercentageSurcharge(percentage);
  }
}
