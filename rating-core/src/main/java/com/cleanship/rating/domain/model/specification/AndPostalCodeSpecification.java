package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

record AndPostalCodeSpecification(PostalCodeSpecification left, PostalCodeSpecification right)
    implements PostalCodeSpecification {
  public AndPostalCodeSpecification {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
  }

  public static AndPostalCodeSpecification of(
      PostalCodeSpecification left, PostalCodeSpecification right) {
    return new AndPostalCodeSpecification(left, right);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return left.isSatisfiedBy(postalCode) && right.isSatisfiedBy(postalCode);
  }
}
