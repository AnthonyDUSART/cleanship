package com.cleanship.rating.domain.model;

public record Version(long value) {
  public static Version of(long value) {
    return new Version(value);
  }
}
