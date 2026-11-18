package com.cleanship.rating.domain.model.pricing;

import com.cleanship.rating.domain.model.money.Money;

public record PriceBreakdown(Money basePrice) {
  public static PriceBreakdown of(Money basePrice) {
    return new PriceBreakdown(basePrice);
  }
}
