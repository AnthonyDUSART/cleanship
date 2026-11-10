package com.cleanship.rating.domain.model;

public sealed interface PostalCode permits DefinedPostalCode, NoPostalCode {
  public static PostalCode of(String value) {
    return DefinedPostalCode.of(value);
  }
}
