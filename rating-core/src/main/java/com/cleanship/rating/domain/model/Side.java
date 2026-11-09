package com.cleanship.rating.domain.model;

public record Side(int millimeters) {
  public static Side of(int millimeters) {
    return new Side(millimeters);
  }
}
