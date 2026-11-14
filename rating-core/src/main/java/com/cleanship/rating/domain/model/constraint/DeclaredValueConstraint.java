package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.InvalidDeclaredValueConstraintException;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.Objects;

public record DeclaredValueConstraint(Money minInclusive, Money maxInclusive)
    implements ShipmentConstraint {
  public DeclaredValueConstraint {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (!minInclusive.currency().equals(maxInclusive.currency())) {
      throw CurrencyMismatchException.of(minInclusive.currency(), maxInclusive.currency());
    }

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw InvalidDeclaredValueConstraintException.of(minInclusive, maxInclusive);
    }
  }

  public static DeclaredValueConstraint of(Money minInclusive, Money maxInclusive) {
    return new DeclaredValueConstraint(minInclusive, maxInclusive);
  }

  @Override
  public boolean isSatisfiedBy(Shipment shipment) {
    Objects.requireNonNull(shipment);

    if (!shipment.declaredValue().currency().equals(minInclusive.currency())) {
      throw CurrencyMismatchException.of(
          minInclusive.currency(), shipment.declaredValue().currency());
    }

    return shipment.declaredValue().isWithin(minInclusive, maxInclusive);
  }
}
