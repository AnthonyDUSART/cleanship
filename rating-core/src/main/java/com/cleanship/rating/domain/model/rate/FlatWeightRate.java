package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Weight;

public record FlatWeightRate(Weight minInclusive, Weight maxInclusive, Money price) {
  public static FlatWeightRate of(Weight minInclusive, Weight maxInclusive, Money price) {
    return new FlatWeightRate(minInclusive, maxInclusive, price);
  }
}
