package com.cleanship.rating.domain.model.pricing;

import com.cleanship.rating.domain.model.money.Money;
import java.util.List;
import java.util.Objects;

public record PriceBreakdown(Money basePrice, List<AppliedPriceModifier> appliedPriceModifiers) {
  public PriceBreakdown {
    Objects.requireNonNull(basePrice);
    Objects.requireNonNull(appliedPriceModifiers);

    appliedPriceModifiers = List.copyOf(appliedPriceModifiers);
  }

  public static PriceBreakdown of(Money basePrice) {
    return new PriceBreakdown(basePrice, List.of());
  }
}
