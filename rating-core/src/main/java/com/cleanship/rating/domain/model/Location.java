package com.cleanship.rating.domain.model;

public record Location(Country country) {
  public static Location of(Country country) {
    return new Location(country);
  }
}
