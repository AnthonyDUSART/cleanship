package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidCurrencyException;
import java.util.Objects;
import java.util.regex.Pattern;

public record Currency(String code) {
  private static final Pattern ISO_4217 = Pattern.compile("^[A-Z]{3}$");

  public Currency {
    Objects.requireNonNull(code);

    if (!ISO_4217.matcher(code).matches()) {
      throw InvalidCurrencyException.of(code);
    }
  }

  public static Currency of(String code) {
    return new Currency(code);
  }
}
