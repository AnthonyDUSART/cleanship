package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidCountryException;
import java.util.Objects;
import java.util.regex.Pattern;

public record Country(String code) {
  private static final Pattern ISO_ALPHA_2 = Pattern.compile("^[A-Z]{2}$");

  public Country {
    Objects.requireNonNull(code);

    if (!ISO_ALPHA_2.matcher(code).matches()) {
      throw InvalidCountryException.of(code);
    }
  }

  public static Country of(String code) {
    return new Country(code);
  }
}
