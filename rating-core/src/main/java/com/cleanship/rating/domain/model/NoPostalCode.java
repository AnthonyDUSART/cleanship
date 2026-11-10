package com.cleanship.rating.domain.model;

public enum NoPostalCode implements PostalCode {
  INSTANCE;

  public static NoPostalCode of() {
    return INSTANCE;
  }

  @Override
  public boolean startsWith(String value) {
    return false;
  }
}
