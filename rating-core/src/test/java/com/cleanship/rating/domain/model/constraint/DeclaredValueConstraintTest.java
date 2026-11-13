package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.InvalidDeclaredValueConstraintException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import org.junit.jupiter.api.Test;

final class DeclaredValueConstraintTest {
  private static final Money MIN_INCLUSIVE = Money.zero(Currency.of("EUR"));

  private static final Money MAX_INCLUSIVE = Money.of(300_00, Currency.of("EUR"));

  @Test
  void should_store_min_inclusive() {
    DeclaredValueConstraint constraint = DeclaredValueConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MIN_INCLUSIVE, constraint.minInclusive());
  }

  @Test
  void should_store_max_inclusive() {
    DeclaredValueConstraint constraint = DeclaredValueConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MAX_INCLUSIVE, constraint.maxInclusive());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> DeclaredValueConstraint.of(null, MAX_INCLUSIVE));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> DeclaredValueConstraint.of(MIN_INCLUSIVE, null));
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_greater_than_max_inclusive() {
    assertThrows(
        InvalidDeclaredValueConstraintException.class,
        () -> DeclaredValueConstraint.of(MAX_INCLUSIVE, MIN_INCLUSIVE));
  }

  @Test
  void should_be_satisfied() {
    Shipment shipment =
        Shipment.of(
            Location.of(Country.of("FR")),
            Location.of(Country.of("FR")),
            Parcel.of(Weight.of(100)),
            Money.of(100_00, Currency.of("EUR")));

    DeclaredValueConstraint constraint = DeclaredValueConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertTrue(constraint.isSatisfiedBy(shipment));
  }
}
