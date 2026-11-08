package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidMoneyExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidMoneyException exception = InvalidMoneyException.of(-20_00);

    assertEquals(exception.getMessage(), "Money \"-2000\" is invalid");
  }
}
