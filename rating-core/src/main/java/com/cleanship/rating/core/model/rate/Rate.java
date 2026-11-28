package com.cleanship.rating.core.model.rate;

import com.cleanship.rating.core.model.money.Currency;
import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.shipment.Shipment;
import com.cleanship.rating.core.model.shipment.Weight;
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

  public boolean overlaps(Rate other);

  public Currency currency();
}
