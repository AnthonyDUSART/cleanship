package com.cleanship.rating.domain.model.pricing;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public record AppliedPriceModifier(
    PriceModifier priceModifier, Money priceBefore, Money priceAfter) {
  public AppliedPriceModifier {
    Objects.requireNonNull(priceModifier);
    Objects.requireNonNull(priceBefore);
    Objects.requireNonNull(priceAfter);

    if (!priceBefore.currency().equals(priceAfter.currency())) {
      throw CurrencyMismatchException.of(priceBefore.currency(), priceAfter.currency());
    }
  }

  public static AppliedPriceModifier of(
      PriceModifier priceModifier, Money priceBefore, Money priceAfter) {
    return new AppliedPriceModifier(priceModifier, priceBefore, priceAfter);
  }
}
