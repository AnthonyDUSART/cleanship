package com.cleanship.rating.domain.model.pricing;

import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record PriceBreakdown(Money basePrice, List<AppliedPriceModifier> appliedPriceModifiers) {
  public PriceBreakdown {
    Objects.requireNonNull(basePrice);
    Objects.requireNonNull(appliedPriceModifiers);

    appliedPriceModifiers = List.copyOf(appliedPriceModifiers);
  }

  public static PriceBreakdown of(Money basePrice) {
    return new PriceBreakdown(basePrice, List.of());
  }

  public PriceBreakdown apply(PriceModifier priceModifier, Shipment shipment) {
    Money priceBefore =
        appliedPriceModifiers.isEmpty() ? basePrice : appliedPriceModifiers.getLast().priceAfter();

    Optional<Money> priceAfter = priceModifier.apply(priceBefore, shipment);

    if (priceAfter.isEmpty()) {
      return this;
    }

    List<AppliedPriceModifier> updatedAppliedPriceModifiers =
        new ArrayList<>(appliedPriceModifiers);
    updatedAppliedPriceModifiers.add(
        AppliedPriceModifier.of(priceModifier, priceBefore, priceAfter.get()));

    return new PriceBreakdown(basePrice, updatedAppliedPriceModifiers);
  }
}
