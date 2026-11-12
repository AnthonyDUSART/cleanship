package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.exception.InvalidFlatWeightRateException;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.Objects;
import java.util.Optional;

public record FlatWeightRate(Weight minInclusive, Weight maxInclusive, Money price)
    implements Rate {
  public FlatWeightRate {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);
    Objects.requireNonNull(price);

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw InvalidFlatWeightRateException.of(minInclusive, maxInclusive);
    }
  }

  public static FlatWeightRate of(Weight minInclusive, Weight maxInclusive, Money price) {
    return new FlatWeightRate(minInclusive, maxInclusive, price);
  }

  @Override
  public Optional<Money> evaluatePrice(Shipment shipment) {
    Objects.requireNonNull(shipment);

    return shipment.parcel().weight().isWithin(minInclusive, maxInclusive)
        ? Optional.of(price)
        : Optional.empty();
  }
}
