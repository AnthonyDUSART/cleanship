package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;

public sealed interface PostalCodeSpecification
    permits AnyPostalCodeSpecification,
        StartsWithMatchingPostalCodeSpecification,
        EndsWithMatchingPostalCodeSpecification,
        ContainsMatchingPostalCodeSpecification,
        EqualsMatchingPostalCodeSpecification,
        AndPostalCodeSpecification {
  public boolean isSatisfiedBy(PostalCode postalCode);

  public default PostalCodeSpecification and(PostalCodeSpecification postalCodeSpecification) {
    return AndPostalCodeSpecification.of(this, postalCodeSpecification);
  }
}
