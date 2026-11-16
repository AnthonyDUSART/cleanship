package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Money;

public record FreeThresholdDiscount(Money minDeclaredValue) {
  public static FreeThresholdDiscount of(Money minDeclaredValue) {
    return new FreeThresholdDiscount(minDeclaredValue);
  }
}
