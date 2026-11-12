package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.Objects;

public record FlatWeightRate(Weight minInclusive, Weight maxInclusive, Money price) {
  public FlatWeightRate {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);
    Objects.requireNonNull(price);
  }

  public static FlatWeightRate of(Weight minInclusive, Weight maxInclusive, Money price) {
    return new FlatWeightRate(minInclusive, maxInclusive, price);
  }
}
