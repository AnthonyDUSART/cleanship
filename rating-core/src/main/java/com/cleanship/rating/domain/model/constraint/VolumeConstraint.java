package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.exception.InvalidVolumeConstraintException;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Volume;
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
    return shipment.parcel().dimension().isVolumeWithin(minInclusive, maxInclusive);
  }
}
