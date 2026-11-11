package com.cleanship.rating.domain.model.zone;

public record ZoneName(String value) {
  public static ZoneName of(String value) {
    return new ZoneName(value);
  }
}
