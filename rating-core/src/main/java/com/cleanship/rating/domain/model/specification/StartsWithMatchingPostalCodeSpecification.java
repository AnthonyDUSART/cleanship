package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

public record StartsWithMatchingPostalCodeSpecification(String prefix)
    implements PostalCodeSpecification {
  public StartsWithMatchingPostalCodeSpecification {
    Objects.requireNonNull(prefix);
  }

  public static StartsWithMatchingPostalCodeSpecification of(String prefix) {
    return new StartsWithMatchingPostalCodeSpecification(prefix);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    return postalCode.startsWith(prefix);
  }
}
