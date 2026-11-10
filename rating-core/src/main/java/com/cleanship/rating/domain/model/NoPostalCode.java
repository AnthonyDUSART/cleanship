package com.cleanship.rating.domain.model;

public enum NoPostalCode implements PostalCode {
  INSTANCE;

  public static NoPostalCode of() {
    return INSTANCE;
  }
}
