package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;

public sealed interface PostalCodeSpecification permits AnyPostalCodeSpecification {
  public boolean isSatisfiedBy(PostalCode postalCode);
}
