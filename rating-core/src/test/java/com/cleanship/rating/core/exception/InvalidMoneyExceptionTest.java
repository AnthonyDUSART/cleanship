package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidMoneyExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidMoneyException exception = InvalidMoneyException.of(-20_00);

    assertEquals("Money \"-2000\" is invalid", exception.getMessage());
  }
}
