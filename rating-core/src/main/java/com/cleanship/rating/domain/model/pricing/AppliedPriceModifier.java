package com.cleanship.rating.domain.model.pricing;

import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Money;

public record AppliedPriceModifier(
    PriceModifier priceModifier, Money priceBefore, Money priceAfter) {
  public static AppliedPriceModifier of(
      PriceModifier priceModifier, Money priceBefore, Money priceAfter) {
    return new AppliedPriceModifier(priceModifier, priceBefore, priceAfter);
  }
}
