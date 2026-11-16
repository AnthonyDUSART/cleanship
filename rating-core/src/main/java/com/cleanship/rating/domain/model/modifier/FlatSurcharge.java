package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Money;

public record FlatSurcharge(Money amount) {
  public static FlatSurcharge of(Money amount) {
    return new FlatSurcharge(amount);
  }
}
