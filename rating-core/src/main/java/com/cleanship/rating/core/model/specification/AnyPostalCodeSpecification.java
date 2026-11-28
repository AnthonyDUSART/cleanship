package com.cleanship.rating.core.model.specification;

import com.cleanship.rating.core.model.location.PostalCode;
import java.util.Objects;

public enum AnyPostalCodeSpecification implements PostalCodeSpecification {
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
