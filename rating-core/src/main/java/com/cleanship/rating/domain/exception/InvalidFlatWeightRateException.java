package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.Objects;

public final class InvalidFlatWeightRateException extends DomainException {
  private InvalidFlatWeightRateException(Weight minInclusive, Weight maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    super(
        String.format(
            "Flat weight rate minInclusive \"%d\" cannot be greater than maxInclusive \"%d\"",
            minInclusive.grams(), maxInclusive.grams()));
  }

  public static InvalidFlatWeightRateException of(Weight minInclusive, Weight maxInclusive) {
    return new InvalidFlatWeightRateException(minInclusive, maxInclusive);
  }
}
