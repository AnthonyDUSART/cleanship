package com.cleanship.rating.domain.model.modifier;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.money.Percentage;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.Objects;
import java.util.Optional;

public record PercentageSurcharge(Percentage percentage) implements PriceModifier {
  public PercentageSurcharge {
    Objects.requireNonNull(percentage);
  }

  public static PercentageSurcharge of(Percentage percentage) {
    return new PercentageSurcharge(percentage);
  }

  @Override
  public Optional<Money> apply(Money currentPrice, Shipment shipment) {
    Objects.requireNonNull(currentPrice);
    Objects.requireNonNull(shipment);

    Money surcharge = currentPrice.applyPercentage(percentage);

    return Optional.of(currentPrice.add(surcharge));
  }
}
