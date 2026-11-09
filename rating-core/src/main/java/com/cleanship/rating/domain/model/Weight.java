package com.cleanship.rating.domain.model;

public record Weight(int grams) {
  public static Weight of(int grams) {
    return new Weight(grams);
  }
}
