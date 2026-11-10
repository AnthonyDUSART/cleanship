package com.cleanship.rating.domain.exception;

public final class InvalidVolumeException extends DomainException {
  private InvalidVolumeException(long cubicMillimeters) {
    super(String.format("Volume \"%d\" is invalid", cubicMillimeters));
  }

  public static InvalidVolumeException of(long cubicMillimeters) {
    return new InvalidVolumeException(cubicMillimeters);
  }
}
