package com.cleanship.rating.domain.model.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.PriceBreakdownContinuityException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class PriceBreakdownTest {
  private static final Money BASE_PRICE = Money.of(20_00, Currency.of("EUR"));

  private static final PriceModifier FLAT_SURCHARGE =
      PriceModifier.flatSurcharge(Money.of(5_00, Currency.of("EUR")));

  private static final Money PRICE_AFTER = Money.of(25_00, Currency.of("EUR"));

  private static final Shipment SHIPMENT =
      Shipment.of(
          Location.of(Country.of("FR")),
          Location.of(Country.of("FR")),
          Parcel.of(Weight.of(250)),
          Money.of(50_00, Currency.of("EUR")));

  @Test
  void should_store_base_price() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    assertEquals(BASE_PRICE, priceBreakdown.basePrice());
  }

  @Test
  void should_throw_exception_when_applied_price_modifiers_is_null() {
    assertThrows(NullPointerException.class, () -> new PriceBreakdown(BASE_PRICE, null));
  }

  @Test
  void should_throw_exception_when_applied_price_modifiers_have_currency_mismatch() {
    List<AppliedPriceModifier> appliedPriceModifiers =
        List.of(
            AppliedPriceModifier.of(FLAT_SURCHARGE, BASE_PRICE, PRICE_AFTER),
            AppliedPriceModifier.of(
                FLAT_SURCHARGE, PRICE_AFTER, Money.of(30_00, Currency.of("EUR"))),
            AppliedPriceModifier.of(
                FLAT_SURCHARGE,
                Money.of(30_00, Currency.of("USD")),
                Money.of(35_00, Currency.of("USD"))));

    assertThrows(
        CurrencyMismatchException.class,
        () -> new PriceBreakdown(BASE_PRICE, appliedPriceModifiers));
  }

  @Test
  void should_throw_exception_when_applied_price_modifiers_have_bad_continuity() {
    List<AppliedPriceModifier> appliedPriceModifiers =
        List.of(
            AppliedPriceModifier.of(FLAT_SURCHARGE, BASE_PRICE, PRICE_AFTER),
            AppliedPriceModifier.of(
                FLAT_SURCHARGE, PRICE_AFTER, Money.of(30_00, Currency.of("EUR"))),
            AppliedPriceModifier.of(
                FLAT_SURCHARGE, PRICE_AFTER, Money.of(30_00, Currency.of("EUR"))));

    assertThrows(
        PriceBreakdownContinuityException.class,
        () -> new PriceBreakdown(BASE_PRICE, appliedPriceModifiers));
  }

  @Test
  void should_copy_of_applied_price_modifiers() {
    List<AppliedPriceModifier> appliedPriceModifiers = new ArrayList<>();
    appliedPriceModifiers.add(AppliedPriceModifier.of(FLAT_SURCHARGE, BASE_PRICE, BASE_PRICE));

    assertNotSame(
        appliedPriceModifiers,
        new PriceBreakdown(BASE_PRICE, appliedPriceModifiers).appliedPriceModifiers());
  }

  @Test
  void should_throw_exception_when_base_price_is_null() {
    assertThrows(NullPointerException.class, () -> PriceBreakdown.of(null));
  }

  @Test
  void should_apply_successfully() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    PriceBreakdown updatedPriceBreakdown = priceBreakdown.apply(FLAT_SURCHARGE, SHIPMENT);

    List<AppliedPriceModifier> expectedAppliedPriceModifiers =
        List.of(AppliedPriceModifier.of(FLAT_SURCHARGE, BASE_PRICE, PRICE_AFTER));

    PriceBreakdown expected = new PriceBreakdown(BASE_PRICE, expectedAppliedPriceModifiers);

    assertEquals(expected, updatedPriceBreakdown);
  }

  @Test
  void should_apply_with_empty_successfully() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    PriceModifier freeThresholdDiscount =
        PriceModifier.freeThresholdDiscount(Money.of(100_00, Currency.of("EUR")));

    PriceBreakdown updatedPriceBreakdown = priceBreakdown.apply(freeThresholdDiscount, SHIPMENT);

    PriceBreakdown expected = new PriceBreakdown(BASE_PRICE, List.of());

    assertEquals(expected, updatedPriceBreakdown);
  }

  @Test
  void should_throw_exception_when_apply_with_null_price_modifier() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    assertThrows(NullPointerException.class, () -> priceBreakdown.apply(null, SHIPMENT));
  }

  @Test
  void should_throw_exception_when_apply_with_null_shipment() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    assertThrows(NullPointerException.class, () -> priceBreakdown.apply(FLAT_SURCHARGE, null));
  }

  @Test
  void should_have_valid_final_price_with_applied_price_modifiers() {
    List<AppliedPriceModifier> appliedPriceModifiers =
        List.of(AppliedPriceModifier.of(FLAT_SURCHARGE, BASE_PRICE, PRICE_AFTER));

    PriceBreakdown priceBreakdown = new PriceBreakdown(BASE_PRICE, appliedPriceModifiers);

    assertEquals(PRICE_AFTER, priceBreakdown.finalPrice());
  }

  @Test
  void should_have_valid_final_price_without_applied_price_modifiers() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    assertEquals(BASE_PRICE, priceBreakdown.finalPrice());
  }

  @ParameterizedTest
  @CsvSource({"1, 2, -1", "1, 1, 0", "2, 1, 1"})
  void should_compare_to_successfully(
      int currentFinalPriceInCents, int otherFinalPriceInCents, int comparisonResult) {
    PriceBreakdown current =
        PriceBreakdown.of(Money.of(currentFinalPriceInCents, Currency.of("EUR")));

    PriceBreakdown other = PriceBreakdown.of(Money.of(otherFinalPriceInCents, Currency.of("EUR")));

    assertEquals(comparisonResult, current.compareTo(other));
  }

  @Test
  void should_throw_exception_when_compare_to_null() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    assertThrows(NullPointerException.class, () -> priceBreakdown.compareTo(null));
  }
}
