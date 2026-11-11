package com.cleanship.rating.domain.model.zone;

import com.cleanship.rating.domain.exception.InvalidZoneException;
import com.cleanship.rating.domain.model.Location;
import java.util.Objects;
import java.util.Set;

public record Zone(ZoneName name, Set<Subdivision> subdivisions) {
  public Zone {
    Objects.requireNonNull(name);
    Objects.requireNonNull(subdivisions);

    if (subdivisions.isEmpty()) {
      throw InvalidZoneException.of(name);
    }

    subdivisions = Set.copyOf(subdivisions);
  }

  public static Zone of(ZoneName name, Set<Subdivision> subdivisions) {
    return new Zone(name, subdivisions);
  }

  public boolean isSatisfiedBy(Location location) {
    return subdivisions.stream().anyMatch(subdivision -> subdivision.isSatisfiedBy(location));
  }
}
