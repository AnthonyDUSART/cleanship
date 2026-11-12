package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.location.PostalCode;
import java.util.Objects;

enum AnyPostalCodeSpecification implements PostalCodeSpecification {
  INSTANCE;

  public static AnyPostalCodeSpecification of() {
    return INSTANCE;
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return true;
  }
}
