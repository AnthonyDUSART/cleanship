package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.Objects;
import java.util.Optional;

public sealed interface PriceModifier permits FreeThresholdDiscount {
  public Optional<Money> apply(Money currentPrice, Shipment shipment);

  public static PriceModifier freeThresholdDiscount(Money minDeclaredValue) {
    Objects.requireNonNull(minDeclaredValue);

    return FreeThresholdDiscount.of(minDeclaredValue);
  }
}
