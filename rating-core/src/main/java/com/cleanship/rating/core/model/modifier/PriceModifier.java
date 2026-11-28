package com.cleanship.rating.core.model.modifier;

import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.money.Percentage;
import com.cleanship.rating.core.model.shipment.Shipment;
import java.util.Objects;
import java.util.Optional;

public sealed interface PriceModifier
    permits FreeThresholdDiscount, FlatSurcharge, PercentageSurcharge {
  public Optional<Money> apply(Money currentPrice, Shipment shipment);

  public static PriceModifier freeThresholdDiscount(Money minDeclaredValue) {
    Objects.requireNonNull(minDeclaredValue);

    return FreeThresholdDiscount.of(minDeclaredValue);
  }

  public static PriceModifier flatSurcharge(Money amount) {
    Objects.requireNonNull(amount);

    return FlatSurcharge.of(amount);
  }

  public static PriceModifier percentageSurcharge(Percentage percentage) {
    Objects.requireNonNull(percentage);

    return PercentageSurcharge.of(percentage);
  }
}
