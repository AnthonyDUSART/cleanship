package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.Objects;
import java.util.Optional;

public sealed interface Rate permits FlatWeightRate {
  public static Rate flatWeight(Weight minInclusive, Weight maxInclusive, Money price) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);
    Objects.requireNonNull(price);

    return FlatWeightRate.of(minInclusive, maxInclusive, price);
  }

  public Optional<Money> evaluatePrice(Shipment shipment);
}
