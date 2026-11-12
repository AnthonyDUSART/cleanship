package com.cleanship.rating.domain.model.location;

import java.util.Objects;

public record Location(Country country, PostalCode postalCode) {
  public Location {
    Objects.requireNonNull(country);
    Objects.requireNonNull(postalCode);
  }

  public static Location of(Country country) {
    return new Location(country, PostalCode.none());
  }

  public static Location of(Country country, PostalCode postalCode) {
    return new Location(country, postalCode);
  }
}
