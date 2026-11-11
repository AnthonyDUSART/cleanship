package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;

public enum AnyPostalCodeSpecification implements PostalCodeSpecification {
  INSTANCE;

  public static AnyPostalCodeSpecification of() {
    return INSTANCE;
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    // TODO Auto-generated method stub
    return false;
  }
}
