package com.cleanship.rating.domain.model;

import java.util.Objects;

public record PostalCode(String value) {
  public PostalCode {
    Objects.requireNonNull(value);
  }

  public static PostalCode of(String value) {
    return new PostalCode(value);
  }
}
