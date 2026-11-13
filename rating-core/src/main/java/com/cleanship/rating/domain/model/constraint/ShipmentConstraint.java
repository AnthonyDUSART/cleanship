package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.shipment.Shipment;

public sealed interface ShipmentConstraint
    permits DeclaredValueConstraint, VolumeConstraint, DimensionConstraint {
  public boolean isSatisfiedBy(Shipment shipment);
}
