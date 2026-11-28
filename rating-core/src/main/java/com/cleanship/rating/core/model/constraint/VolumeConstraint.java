package com.cleanship.rating.core.model.constraint;

import com.cleanship.rating.core.exception.InvalidVolumeConstraintException;
import com.cleanship.rating.core.model.shipment.Shipment;
import com.cleanship.rating.core.model.shipment.Volume;
import java.util.Objects;

public record VolumeConstraint(Volume minInclusive, Volume maxInclusive)
    implements ShipmentConstraint {
  public VolumeConstraint {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw InvalidVolumeConstraintException.of(minInclusive, maxInclusive);
    }
  }

  public static VolumeConstraint of(Volume minInclusive, Volume maxInclusive) {
    return new VolumeConstraint(minInclusive, maxInclusive);
  }

  @Override
  public boolean isSatisfiedBy(Shipment shipment) {
    Objects.requireNonNull(shipment);

    return shipment.parcel().dimension().isVolumeWithin(minInclusive, maxInclusive);
  }
}
