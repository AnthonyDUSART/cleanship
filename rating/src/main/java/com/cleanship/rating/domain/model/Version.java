package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidVersionException;

public record Version(long value) {
  public Version {
    if (value < 1) {
      throw InvalidVersionException.of(value);
    }
  }

  public static Version of(long value) {
    return new Version(value);
  }

  public static Version initial() {
    return new Version(1);
  }
}
