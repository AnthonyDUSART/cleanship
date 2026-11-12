package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.shipment.Weight;
import org.junit.jupiter.api.Test;

final class InvalidFlatWeightRateExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidFlatWeightRateException exception =
        InvalidFlatWeightRateException.of(Weight.of(2), Weight.of(1));

    assertEquals(
        String.format(
            "Flat weight rate minInclusive \"%d\" cannot be greater than maxInclusive \"%d\"",
            2, 1),
        exception.getMessage());
  }
}
