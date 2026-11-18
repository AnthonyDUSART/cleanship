package com.cleanship.rating.domain.model.pricing;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.PriceBreakdownContinuityException;
import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record PriceBreakdown(Money basePrice, List<AppliedPriceModifier> appliedPriceModifiers)
    implements Comparable<PriceBreakdown> {
  public PriceBreakdown {
    Objects.requireNonNull(basePrice);
    Objects.requireNonNull(appliedPriceModifiers);

    appliedPriceModifiers = List.copyOf(appliedPriceModifiers);

    Money expectedPrice = basePrice;
    for (AppliedPriceModifier appliedPriceModifier : appliedPriceModifiers) {
      if (!expectedPrice.currency().equals(appliedPriceModifier.priceBefore().currency())) {
        throw CurrencyMismatchException.of(
            expectedPrice.currency(), appliedPriceModifier.priceBefore().currency());
      }

      if (!expectedPrice.equals(appliedPriceModifier.priceBefore())) {
        throw PriceBreakdownContinuityException.of(
            expectedPrice, appliedPriceModifier.priceBefore());
      }

      expectedPrice = appliedPriceModifier.priceAfter();
    }
  }

  public static PriceBreakdown of(Money basePrice) {
    return new PriceBreakdown(basePrice, List.of());
  }

  public static PriceBreakdown of(
      Money basePrice, List<AppliedPriceModifier> appliedPriceModifiers) {
    return new PriceBreakdown(basePrice, appliedPriceModifiers);
  }

  public PriceBreakdown apply(PriceModifier priceModifier, Shipment shipment) {
    Objects.requireNonNull(priceModifier);
    Objects.requireNonNull(shipment);

    Money priceBefore = finalPrice();

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

  public Money finalPrice() {
    return appliedPriceModifiers.isEmpty()
        ? basePrice
        : appliedPriceModifiers.getLast().priceAfter();
  }

  @Override
  public int compareTo(PriceBreakdown o) {
    Objects.requireNonNull(o);

    return finalPrice().compareTo(o.finalPrice());
  }
}
