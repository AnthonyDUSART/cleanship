package com.cleanship.rating.domain.exception;

public final class InvalidMoneyException extends DomainException {
  private InvalidMoneyException(long cents) {
    super(String.format("Money \"%d\" is invalid", cents));
  }

  public static InvalidMoneyException of(long cents) {
    return new InvalidMoneyException(cents);
  }
}
