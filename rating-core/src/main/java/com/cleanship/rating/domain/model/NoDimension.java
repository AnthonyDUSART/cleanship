package com.cleanship.rating.domain.model;

public enum NoDimension implements Dimension {
  INSTANCE;

  public static NoDimension of() {
    return INSTANCE;
  }
}
