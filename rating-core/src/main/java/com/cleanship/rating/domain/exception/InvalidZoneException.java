package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.zone.ZoneName;
import java.util.Objects;

public final class InvalidZoneException extends DomainException {
  private InvalidZoneException(ZoneName name) {
    Objects.requireNonNull(name);

    super(String.format("Zone \"%s\" must contain at least one subdivision", name.value()));
  }

  public static InvalidZoneException of(ZoneName name) {
    return new InvalidZoneException(name);
  }
}
