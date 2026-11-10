package com.cleanship.rating.domain.model;

public record Volume(long cubicMillimeters) {
  public static Volume of(long cubicMillimeters) {
    return new Volume(cubicMillimeters);
  }
}
