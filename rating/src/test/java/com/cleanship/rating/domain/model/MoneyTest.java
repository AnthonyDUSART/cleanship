package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test 
    public void should_store_cents() {
        assertEquals(40_00, Money.of(40_00, Currency.of("EUR")).cents());
    }
    
    @Test 
    public void should_store_currency() {
        assertEquals(Currency.of("EUR"), Money.of(40_00, Currency.of("EUR")).currency());
    }

    @Test 
    public void should_throw_exception_when_currency_is_null() {
        assertThrows(
            NullPointerException.class,
            () -> Money.of(2_00, null)
        );
    }

    @Test 
    public void should_have_zero_cents() {
        assertEquals(0, Money.zero(Currency.of("EUR")).cents());
    }
}
