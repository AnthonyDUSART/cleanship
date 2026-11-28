package com.cleanship.rating.core.model.zone;

import com.cleanship.rating.core.exception.InvalidZoneException;
import com.cleanship.rating.core.model.location.Location;
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
    Objects.requireNonNull(location);

    for (Subdivision subdivision : subdivisions) {
      if (subdivision.isSatisfiedBy(location)) {
        return true;
      }
    }

    return false;
  }
}
