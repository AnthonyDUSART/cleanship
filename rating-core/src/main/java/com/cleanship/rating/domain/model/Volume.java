package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidVolumeException;

public record Volume(long cubicMillimeters) {
  public Volume {
    if (0 >= cubicMillimeters) {
      throw InvalidVolumeException.of(cubicMillimeters);
    }
  }

  public static Volume of(long cubicMillimeters) {
    return new Volume(cubicMillimeters);
  }

  public static Volume of(DefinedDimension dimension) {
    return new Volume(
        dimension.length().millimeters()
            * dimension.width().millimeters()
            * dimension.height().millimeters());
  }
}
