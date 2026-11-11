package com.cleanship.rating.domain.model.zone;

public record SubdivisionName(String value) {
  public static SubdivisionName of(String value) {
    return new SubdivisionName(value);
  }
}
