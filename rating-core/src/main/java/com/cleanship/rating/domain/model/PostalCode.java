package com.cleanship.rating.domain.model;

public record PostalCode(String value) {
  public static PostalCode of(String value) {
    return new PostalCode(value);
  }
}
