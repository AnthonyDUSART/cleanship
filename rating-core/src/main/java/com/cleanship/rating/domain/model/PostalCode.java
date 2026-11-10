package com.cleanship.rating.domain.model;

public sealed interface PostalCode permits DefinedPostalCode, NoPostalCode {
  public static PostalCode of(String value) {
    return DefinedPostalCode.of(value);
  }

  public static PostalCode none() {
    return NoPostalCode.of();
  }

  public boolean startsWith(String prefix);

  public boolean endsWith(String suffix);
}
