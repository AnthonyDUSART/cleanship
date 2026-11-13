package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;

public sealed interface ShipmentConstraint
    permits DeclaredValueConstraint, VolumeConstraint, DimensionConstraint {

  public static ShipmentConstraint declaredValue(Money minInclusive, Money maxInclusive) {
    return DeclaredValueConstraint.of(minInclusive, maxInclusive);
  }

  public boolean isSatisfiedBy(Shipment shipment);
}
