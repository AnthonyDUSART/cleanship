package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public record FreeThresholdDiscount(Money minDeclaredValue) {
  public FreeThresholdDiscount {
    Objects.requireNonNull(minDeclaredValue);
  }

  public static FreeThresholdDiscount of(Money minDeclaredValue) {
    return new FreeThresholdDiscount(minDeclaredValue);
  }
}
