package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.model.rate.RateBook;
import java.util.Objects;

public final class Tariff {
  private final TariffId id;

  private final RateBook rateBook;

  private final Version version;

  private Tariff(TariffId id, RateBook rateBook, Version version) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(rateBook);
    Objects.requireNonNull(version);

    this.id = id;
    this.rateBook = rateBook;
    this.version = version;
  }

  public static Tariff create(TariffId id, RateBook rateBook) {
    return new Tariff(id, rateBook, Version.initial());
  }

  public static Tariff reconstitute(TariffId id, RateBook rateBook, Version version) {
    return new Tariff(id, rateBook, version);
  }

  public TariffId id() {
    return id;
  }

  public RateBook rateBook() {
    return rateBook;
  }

  public Version version() {
    return version;
  }
}
