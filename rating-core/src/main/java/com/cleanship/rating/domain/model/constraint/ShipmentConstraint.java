package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.shipment.Shipment;

public interface ShipmentConstraint {
  public boolean isSatisfiedBy(Shipment shipment);
}
