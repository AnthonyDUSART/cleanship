package com.cleanship.rating.core.model.specification;

import com.cleanship.rating.core.exception.InvalidContainsMatchingPostalCodeSpecificationException;
import com.cleanship.rating.core.model.location.PostalCode;
import java.util.Locale;
import java.util.Objects;

public record ContainsMatchingPostalCodeSpecification(String sequence)
    implements PostalCodeSpecification {
  public ContainsMatchingPostalCodeSpecification {
    Objects.requireNonNull(sequence);

    if (sequence.isEmpty()) {
      throw InvalidContainsMatchingPostalCodeSpecificationException.of(sequence);
    }

    sequence = sequence.toUpperCase(Locale.ROOT);
  }

  public static ContainsMatchingPostalCodeSpecification of(String sequence) {
    return new ContainsMatchingPostalCodeSpecification(sequence);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return postalCode.contains(sequence);
  }
}
