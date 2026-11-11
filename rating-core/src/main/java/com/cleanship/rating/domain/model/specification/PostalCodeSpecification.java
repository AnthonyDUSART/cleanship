package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

public sealed interface PostalCodeSpecification
    permits AnyPostalCodeSpecification,
        StartsWithMatchingPostalCodeSpecification,
        EndsWithMatchingPostalCodeSpecification,
        ContainsMatchingPostalCodeSpecification,
        EqualsMatchingPostalCodeSpecification,
        AndPostalCodeSpecification,
        OrPostalCodeSpecification {
  public boolean isSatisfiedBy(PostalCode postalCode);

  public default PostalCodeSpecification and(PostalCodeSpecification postalCodeSpecification) {
    Objects.requireNonNull(postalCodeSpecification);

    return AndPostalCodeSpecification.of(this, postalCodeSpecification);
  }
}
