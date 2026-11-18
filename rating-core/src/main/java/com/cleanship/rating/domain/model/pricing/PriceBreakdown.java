package com.cleanship.rating.domain.model.pricing;

import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public record PriceBreakdown(Money basePrice) {
  public PriceBreakdown {
    Objects.requireNonNull(basePrice);
  }

  public static PriceBreakdown of(Money basePrice) {
    return new PriceBreakdown(basePrice);
  }
}
