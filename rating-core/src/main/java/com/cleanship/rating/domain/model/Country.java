package com.cleanship.rating.domain.model;

public record Country(String code) {
  public static Country of(String code) {
    return new Country(code);
  }
}
