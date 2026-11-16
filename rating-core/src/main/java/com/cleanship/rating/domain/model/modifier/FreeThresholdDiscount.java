package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
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
    // TODO Auto-generated method stub
    return Optional.empty();
  }
}
