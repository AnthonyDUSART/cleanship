package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;
import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TariffNotFoundExceptionTest {
  private static final TariffId TARIFF_ID =
      TariffId.of(UUID.fromString("01a21469-3556-728f-9e0a-b50aa7f28ba1"));

  private static final TenantId TENANT_ID =
      TenantId.of(UUID.fromString("01a21469-72fc-73cc-8401-9b6b786ccbb5"));

  @Test
  void should_have_valid_message() {
    TariffNotFoundException exception = TariffNotFoundException.of(TARIFF_ID, TENANT_ID);

    assertEquals(
        "Tariff \"01a21469-3556-728f-9e0a-b50aa7f28ba1\" for tenant"
            + " \"01a21469-72fc-73cc-8401-9b6b786ccbb5\" not found",
        exception.getMessage());
  }

  @Test
  void should_throw_exception_when_tariff_id_is_null() {
    assertThrows(NullPointerException.class, () -> TariffNotFoundException.of(null, TENANT_ID));
  }

  @Test
  void should_throw_exception_when_tenant_id_is_null() {
    assertThrows(NullPointerException.class, () -> TariffNotFoundException.of(TARIFF_ID, null));
  }
}
