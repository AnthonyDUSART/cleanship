package com.cleanship.rating.domain.model;

public record Dimension(Side length, Side width, Side height) {
  public static Dimension of(Side length, Side width, Side height) {
    return new Dimension(length, width, height);
  }
}
