package com.cleanship.rating.core.model.modifier;

import com.cleanship.rating.core.exception.CurrencyMismatchException;
import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.shipment.Shipment;
import java.util.Objects;
import java.util.Optional;

public record FreeThresholdDiscount(Money minDeclaredValue) implements PriceModifier {
  public FreeThresholdDiscount {
    Objects.requireNonNull(minDeclaredValue);
  }

  public static FreeThresholdDiscount of(Money minDeclaredValue) {
    return new FreeThresholdDiscount(minDeclaredValue);
  }

  @Override
  public Optional<Money> apply(Money currentPrice, Shipment shipment) {
    Objects.requireNonNull(currentPrice);
    Objects.requireNonNull(shipment);

    if (!currentPrice.currency().equals(minDeclaredValue.currency())) {
      throw CurrencyMismatchException.of(minDeclaredValue.currency(), currentPrice.currency());
    }

    if (!shipment.declaredValue().isGreaterThanOrEqualTo(minDeclaredValue)) {
      return Optional.empty();
    }

    return Optional.of(Money.zero(minDeclaredValue.currency()));
  }
}
