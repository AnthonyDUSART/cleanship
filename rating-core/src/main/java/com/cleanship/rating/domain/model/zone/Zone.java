package com.cleanship.rating.domain.model.zone;

import java.util.Set;

public record Zone(ZoneName name, Set<Subdivision> subdivisions) {
  public static Zone of(ZoneName name, Set<Subdivision> subdivisions) {
    return new Zone(name, subdivisions);
  }
}
