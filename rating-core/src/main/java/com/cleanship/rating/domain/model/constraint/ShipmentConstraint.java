package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Volume;
import java.util.Objects;

public sealed interface ShipmentConstraint
    permits DeclaredValueConstraint, VolumeConstraint, DimensionConstraint {

  public static ShipmentConstraint declaredValue(Money minInclusive, Money maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    return DeclaredValueConstraint.of(minInclusive, maxInclusive);
  }

  public static ShipmentConstraint volume(Volume minInclusive, Volume maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    return VolumeConstraint.of(minInclusive, maxInclusive);
  }

  public boolean isSatisfiedBy(Shipment shipment);
}
