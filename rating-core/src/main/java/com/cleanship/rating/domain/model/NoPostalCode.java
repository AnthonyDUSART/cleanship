package com.cleanship.rating.domain.model;

import java.util.Objects;

enum NoPostalCode implements PostalCode {
  INSTANCE;

  public static NoPostalCode of() {
    return INSTANCE;
  }

  @Override
  public boolean startsWith(String prefix) {
    Objects.requireNonNull(prefix);

    return false;
  }

  @Override
  public boolean endsWith(String suffix) {
    Objects.requireNonNull(suffix);

    return false;
  }

  @Override
  public boolean contains(String sequence) {
    Objects.requireNonNull(sequence);

    return false;
  }

  @Override
  public boolean isEqualTo(String value) {
    Objects.requireNonNull(value);

    return false;
  }
}
