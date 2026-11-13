package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.shipment.DefinedDimension;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.Objects;

public record DimensionConstraint(DefinedDimension minInclusive, DefinedDimension maxInclusive)
    implements ShipmentConstraint {
  public DimensionConstraint {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);
  }

  public static DimensionConstraint of(
      DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    return new DimensionConstraint(minInclusive, maxInclusive);
  }

  @Override
  public boolean isSatisfiedBy(Shipment shipment) {
    // TODO Auto-generated method stub
    return false;
  }
}
