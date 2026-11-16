package com.cleanship.rating.domain.model.modifier;

import java.util.Objects;

import com.cleanship.rating.domain.model.money.Money;

public record FlatSurcharge(Money amount) {
  public static FlatSurcharge of(Money amount) {
    Objects.requireNonNull(amount);

    return new FlatSurcharge(amount);
  }
}
