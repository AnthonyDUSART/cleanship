package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;
import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TariffNotFoundExceptionTest {
  @Test
  void should_have_valid_message() {
    TariffNotFoundException exception =
        TariffNotFoundException.of(
            TariffId.of(UUID.fromString("01a21469-3556-728f-9e0a-b50aa7f28ba1")),
            TenantId.of(UUID.fromString("01a21469-72fc-73cc-8401-9b6b786ccbb5")));

    assertEquals(
        "Tariff \"01a21469-3556-728f-9e0a-b50aa7f28ba1\" of tenant"
            + " \"01a21469-72fc-73cc-8401-9b6b786ccbb5\" is not found",
        exception.getMessage());
  }
}
