package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.shipment.DefinedDimension;

public final class InvalidDimensionConstraintException extends DomainException {
  private InvalidDimensionConstraintException(
      DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    super(
        String.format(
            "Dimension constraint minInclusive \"%dx%dx%d\" cannot exceed maxInclusive"
                + " \"%dx%dx%d\"",
            minInclusive.length().millimeters(),
            minInclusive.width().millimeters(),
            minInclusive.height().millimeters(),
            maxInclusive.length().millimeters(),
            maxInclusive.width().millimeters(),
            maxInclusive.height().millimeters()));
  }

  public static InvalidDimensionConstraintException of(
      DefinedDimension minInclusive, DefinedDimension maxInclusive) {
    return new InvalidDimensionConstraintException(minInclusive, maxInclusive);
  }
}
