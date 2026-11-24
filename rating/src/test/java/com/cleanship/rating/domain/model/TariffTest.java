package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.rate.Rate;
import com.cleanship.rating.domain.model.rate.RateBook;
import com.cleanship.rating.domain.model.rate.RateChart;
import com.cleanship.rating.domain.model.shipment.Weight;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TariffTest {
  private static final TariffId ID =
      TariffId.of(UUID.fromString("01a20ad9-3328-73e9-b493-46d6c3ed874b"));

  private static final RateBook RATE_BOOK =
      RateBook.of(
          Set.of(
              RateChart.of(
                  Zone.of(
                      ZoneName.of("France"),
                      Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR")))),
                  Set.of(
                      Rate.flatWeight(
                          Weight.of(1), Weight.of(999), Money.of(6_50, Currency.of("EUR")))))));

  @Test
  void should_store_id() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK);

    assertEquals(ID, tariff.id());
  }

  @Test
  void should_store_rate_book() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK);

    assertEquals(RATE_BOOK, tariff.rateBook());
  }

  @Test
  void should_throw_exception_when_id_is_null() {
    assertThrows(NullPointerException.class, () -> Tariff.create(null, RATE_BOOK));
  }

  @Test
  void should_throw_exception_when_rate_book_is_null() {
    assertThrows(NullPointerException.class, () -> Tariff.create(ID, null));
  }

  @Test
  void should_create_with_initial_version() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK);

    assertEquals(Version.initial(), tariff.version());
  }

  @Test
  void should_reconstitute_successfully() {
    Tariff tariff = Tariff.reconstitute(ID, RATE_BOOK, Version.of(10));

    assertEquals(ID, tariff.id());
    assertEquals(RATE_BOOK, tariff.rateBook());
    assertEquals(Version.of(10), tariff.version());
  }

  @Test
  void should_throw_exception_when_reconstitute_with_null_version() {
    assertThrows(NullPointerException.class, () -> Tariff.reconstitute(ID, RATE_BOOK, null));
  }

  @Test
  void should_be_equals_successfully() {
    Tariff current = Tariff.create(ID, RATE_BOOK);
    Tariff other = Tariff.reconstitute(ID, RATE_BOOK, Version.of(3));

    assertTrue(current.equals(other));
  }

  @Test
  void should_not_be_equals() {
    Tariff current = Tariff.create(ID, RATE_BOOK);
    Tariff other =
        Tariff.create(
            TariffId.of(UUID.fromString("01a20edf-51ce-778e-8363-c2431ec5f043")), RATE_BOOK);

    assertFalse(current.equals(other));
  }
}
