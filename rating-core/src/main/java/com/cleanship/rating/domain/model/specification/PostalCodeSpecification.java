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
        OrPostalCodeSpecification,
        NotPostalCodeSpecification {
  public boolean isSatisfiedBy(PostalCode postalCode);

  public default PostalCodeSpecification and(PostalCodeSpecification postalCodeSpecification) {
    Objects.requireNonNull(postalCodeSpecification);

    return AndPostalCodeSpecification.of(this, postalCodeSpecification);
  }

  public default PostalCodeSpecification or(PostalCodeSpecification postalCodeSpecification) {
    Objects.requireNonNull(postalCodeSpecification);

    return OrPostalCodeSpecification.of(this, postalCodeSpecification);
  }
}
