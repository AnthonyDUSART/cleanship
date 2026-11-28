package com.cleanship.rating.core.model.constraint;

import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.shipment.DefinedDimension;
import com.cleanship.rating.core.model.shipment.Shipment;
import com.cleanship.rating.core.model.shipment.Volume;
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

  public static ShipmentConstraint dimension(
      DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    return DimensionConstraint.of(minInclusive, maxInclusive);
  }

  public boolean isSatisfiedBy(Shipment shipment);
}
