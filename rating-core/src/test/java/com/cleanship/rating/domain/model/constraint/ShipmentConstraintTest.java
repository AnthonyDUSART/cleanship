package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.DefinedDimension;
import com.cleanship.rating.domain.model.shipment.Side;
import com.cleanship.rating.domain.model.shipment.Volume;
import org.junit.jupiter.api.Test;

final class ShipmentConstraintTest {
  @Test
  void should_create_declared_value_constraint() {
    Money amount = Money.zero(Currency.of("EUR"));

    DeclaredValueConstraint expected = DeclaredValueConstraint.of(amount, amount);

    assertEquals(expected, ShipmentConstraint.declaredValue(amount, amount));
  }

  @Test
  void should_throw_exception_when_create_declared_value_constraint_with_null_min_inclusive() {
    Money maxInclusive = Money.zero(Currency.of("EUR"));

    assertThrows(
        NullPointerException.class, () -> ShipmentConstraint.declaredValue(null, maxInclusive));
  }

  @Test
  void should_throw_exception_when_create_declared_value_constraint_with_null_max_inclusive() {
    Money minInclusive = Money.zero(Currency.of("EUR"));

    assertThrows(
        NullPointerException.class, () -> ShipmentConstraint.declaredValue(minInclusive, null));
  }

  @Test
  void should_create_volume_constraint() {
    Volume volume = Volume.of(1);

    VolumeConstraint expected = VolumeConstraint.of(volume, volume);

    assertEquals(expected, ShipmentConstraint.volume(volume, volume));
  }

  @Test
  void should_throw_exception_when_create_volume_constraint_with_null_min_inclusive() {
    Volume maxInclusive = Volume.of(1);

    assertThrows(NullPointerException.class, () -> ShipmentConstraint.volume(null, maxInclusive));
  }

  @Test
  void should_throw_exception_when_create_volume_constraint_with_null_max_inclusive() {
    Volume minInclusive = Volume.of(1);

    assertThrows(NullPointerException.class, () -> ShipmentConstraint.volume(minInclusive, null));
  }

  @Test
  void should_create_dimension_constraint() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

    DimensionConstraint expected = DimensionConstraint.of(dimension, dimension);

    assertEquals(expected, ShipmentConstraint.dimension(dimension, dimension));
  }

  @Test
  void should_throw_exception_when_create_dimension_constraint_with_null_min_inclusive() {
    DefinedDimension maxInclusive = DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

    assertThrows(
        NullPointerException.class, () -> ShipmentConstraint.dimension(null, maxInclusive));
  }

  @Test
  void should_throw_exception_when_create_dimension_constraint_with_null_max_inclusive() {
    DefinedDimension minInclusive = DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

    assertThrows(
        NullPointerException.class, () -> ShipmentConstraint.dimension(minInclusive, null));
  }
}
