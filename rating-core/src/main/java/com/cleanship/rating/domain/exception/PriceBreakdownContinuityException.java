package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public final class PriceBreakdownContinuityException extends DomainException {
  private PriceBreakdownContinuityException(Money expectedPriceBefore, Money actualPriceBefore) {
    Objects.requireNonNull(expectedPriceBefore);

    super(
        String.format(
            "Price breakdown expected priceBefore \"%d %s\", actual \"%d %s\"",
            expectedPriceBefore.cents(),
            expectedPriceBefore.currency().code(),
            actualPriceBefore.cents(),
            actualPriceBefore.currency().code()));
  }

  public static PriceBreakdownContinuityException of(
      Money expectedPriceBefore, Money actualPriceBefore) {
    return new PriceBreakdownContinuityException(expectedPriceBefore, actualPriceBefore);
  }
}
