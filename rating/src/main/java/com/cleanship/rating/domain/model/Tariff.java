package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.event.DomainEvent;
import com.cleanship.rating.domain.event.TariffCreated;
import com.cleanship.rating.domain.event.TariffUpdated;
import com.cleanship.rating.domain.model.rate.RateBook;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Tariff {
  private final TariffId id;

  private final RateBook rateBook;

  private final Version version;

  private final List<DomainEvent> uncommittedEvents;

  private Tariff(
      TariffId id, RateBook rateBook, Version version, List<DomainEvent> uncommittedEvents) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(rateBook);
    Objects.requireNonNull(version);

    this.id = id;
    this.rateBook = rateBook;
    this.version = version;
    this.uncommittedEvents = List.copyOf(uncommittedEvents);
  }

  public static Tariff create(TariffId id, RateBook rateBook, Instant now) {
    Objects.requireNonNull(now);

    Version version = Version.initial();

    List<DomainEvent> uncommittedEvents = List.of(TariffCreated.of(id, rateBook, version, now));

    return new Tariff(id, rateBook, version, uncommittedEvents);
  }

  public static Tariff reconstitute(TariffId id, RateBook rateBook, Version version) {
    return new Tariff(id, rateBook, version, List.of());
  }

  public Tariff update(RateBook updatedRateBook, Instant now) {
    Objects.requireNonNull(rateBook);
    Objects.requireNonNull(now);

    if (rateBook.equals(updatedRateBook)) {
      return this;
    }

    Version updatedVersion = version.next();

    List<DomainEvent> updatedUncommittedEvents = new ArrayList<>(uncommittedEvents);

    updatedUncommittedEvents.add(TariffUpdated.of(id, updatedRateBook, updatedVersion, now));

    return new Tariff(id, updatedRateBook, updatedVersion, updatedUncommittedEvents);
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

  public List<DomainEvent> uncommittedEvents() {
    return uncommittedEvents;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Tariff other && id.equals(other.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
