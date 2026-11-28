package com.cleanship.rating.service.domain.model;

import com.cleanship.rating.core.model.pricing.PriceBreakdown;
import com.cleanship.rating.core.model.rate.RateBook;
import com.cleanship.rating.core.model.shipment.Shipment;
import com.cleanship.rating.service.domain.event.DomainEvent;
import com.cleanship.rating.service.domain.event.TariffCreated;
import com.cleanship.rating.service.domain.event.TariffUpdated;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Tariff {
  private final TariffId id;

  private final TenantId tenantId;

  private final RateBook rateBook;

  private final Version version;

  private final List<DomainEvent> uncommittedEvents;

  private Tariff(
      TariffId id,
      TenantId tenantId,
      RateBook rateBook,
      Version version,
      List<DomainEvent> uncommittedEvents) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(tenantId);
    Objects.requireNonNull(rateBook);
    Objects.requireNonNull(version);

    this.id = id;
    this.tenantId = tenantId;
    this.rateBook = rateBook;
    this.version = version;
    this.uncommittedEvents = List.copyOf(uncommittedEvents);
  }

  public static Tariff create(TariffId id, TenantId tenantId, RateBook rateBook, Instant now) {
    Objects.requireNonNull(now);

    Version version = Version.initial();

    List<DomainEvent> uncommittedEvents =
        List.of(TariffCreated.of(id, tenantId, rateBook, version, now));

    return new Tariff(id, tenantId, rateBook, version, uncommittedEvents);
  }

  public static Tariff reconstitute(
      TariffId id, TenantId tenantId, RateBook rateBook, Version version) {
    return new Tariff(id, tenantId, rateBook, version, List.of());
  }

  public Tariff update(RateBook updatedRateBook, Instant now) {
    Objects.requireNonNull(updatedRateBook);
    Objects.requireNonNull(now);

    if (rateBook.equals(updatedRateBook)) {
      return this;
    }

    Version updatedVersion = version.next();

    List<DomainEvent> updatedUncommittedEvents = new ArrayList<>(uncommittedEvents);

    updatedUncommittedEvents.add(
        TariffUpdated.of(id, tenantId, updatedRateBook, updatedVersion, now));

    return new Tariff(id, tenantId, updatedRateBook, updatedVersion, updatedUncommittedEvents);
  }

  public Optional<PriceBreakdown> evaluate(Shipment shipment) {
    Objects.requireNonNull(shipment);

    return rateBook.evaluate(shipment);
  }

  public TariffId id() {
    return id;
  }

  public TenantId tenantId() {
    return tenantId;
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
    return obj instanceof Tariff other && id.equals(other.id) && tenantId.equals(other.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tenantId);
  }
}
