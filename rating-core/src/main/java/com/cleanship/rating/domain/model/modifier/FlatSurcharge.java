package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.Objects;
import java.util.Optional;

public record FlatSurcharge(Money amount) implements PriceModifier {
  public FlatSurcharge {
    Objects.requireNonNull(amount);
  }

  public static FlatSurcharge of(Money amount) {
    return new FlatSurcharge(amount);
  }

  @Override
  public Optional<Money> apply(Money currentPrice, Shipment shipment) {
    Objects.requireNonNull(currentPrice);

    if (!shipment.declaredValue().currency().equals(amount.currency())) {
      throw CurrencyMismatchException.of(amount.currency(), shipment.declaredValue().currency());
    }

    return Optional.of(currentPrice.add(amount));
  }
}
