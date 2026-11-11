package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidZoneNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class ZoneNameTest {
  @Test
  void should_store_value() {
    assertEquals("America", ZoneName.of("  America ").value());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> ZoneName.of(null));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "",
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget"
            + " dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,"
            + " nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium"
            + " quis,."
      })
  void should_throw_exception_when_zone_name_is_invalid(String value) {
    assertThrows(InvalidZoneNameException.class, () -> ZoneName.of(value));
  }
}
