package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

public record ContainsPostalCodeSpecification(String sequence) implements PostalCodeSpecification {
  public ContainsPostalCodeSpecification {
    Objects.requireNonNull(sequence);
  }

  public static ContainsPostalCodeSpecification of(String sequence) {
    return new ContainsPostalCodeSpecification(sequence);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    return postalCode.contains(sequence);
  }
}
