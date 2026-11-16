package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.Objects;
import java.util.Optional;

public record FlatSurcharge(Money amount) implements PriceModifier {
  public static FlatSurcharge of(Money amount) {
    Objects.requireNonNull(amount);

    return new FlatSurcharge(amount);
  }

  @Override
  public Optional<Money> apply(Money currentPrice, Shipment shipment) {
    return Optional.of(currentPrice.add(amount));
  }
}
