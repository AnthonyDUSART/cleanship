package com.cleanship.rating.domain.model.modifier;

import java.util.Objects;
import java.util.Optional;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;

public record FlatSurcharge(Money amount) implements PriceModifier {
  public static FlatSurcharge of(Money amount) {
    Objects.requireNonNull(amount);

    return new FlatSurcharge(amount);
  }

  @Override
  public Optional<Money> apply(Money currentPrice, Shipment shipment) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }
}
