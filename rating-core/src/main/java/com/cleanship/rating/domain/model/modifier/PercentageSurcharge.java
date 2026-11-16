package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Percentage;
import java.util.Objects;

public record PercentageSurcharge(Percentage percentage) {
  public PercentageSurcharge {
    Objects.requireNonNull(percentage);
  }

  public static PercentageSurcharge of(Percentage percentage) {
    return new PercentageSurcharge(percentage);
  }
}
