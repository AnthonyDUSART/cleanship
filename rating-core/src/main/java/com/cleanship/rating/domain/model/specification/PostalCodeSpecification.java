package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;

public sealed interface PostalCodeSpecification
    permits AnyPostalCodeSpecification,
        StartsWithMatchingPostalCodeSpecification,
        EndsWithMatchingPostalCodeSpecification,
        ContainsMatchingPostalCodeSpecification,
        EqualsMatchingPostalCodeSpecification {
  public boolean isSatisfiedBy(PostalCode postalCode);
}
