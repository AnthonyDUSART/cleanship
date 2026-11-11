package com.cleanship.rating.domain.model;

import java.util.Objects;

public record Location(Country country, PostalCode postalCode) {
  public Location {
    Objects.requireNonNull(country);
  }

  public static Location of(Country country) {
    return new Location(country, NoPostalCode.of());
  }

  public static Location of(Country country, PostalCode postalCode) {
    return new Location(country, postalCode);
  }
}
