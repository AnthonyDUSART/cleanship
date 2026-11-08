package com.cleanship.rating.domain.model;

public record Currency(String code) {
    public static Currency of(String code) {
        return new Currency(code);
    }
}
