package com.cleanship.rating.domain.model;

import java.util.Objects;

public enum NoPostalCode implements PostalCode {
  INSTANCE;

  public static NoPostalCode of() {
    return INSTANCE;
  }

  @Override
  public boolean startsWith(String value) {
    Objects.requireNonNull(value);

    return false;
  }

  @Override
  public boolean endsWith(String value) {
    Objects.requireNonNull(value);

    return false;
  }
}
