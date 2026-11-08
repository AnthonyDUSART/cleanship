package com.cleanship.rating.domain.model;

import java.util.Objects;

public record Currency(String code) {
    public Currency {
        Objects.requireNonNull(code);
    }
    
    public static Currency of(String code) {
        return new Currency(code);
    }
}
