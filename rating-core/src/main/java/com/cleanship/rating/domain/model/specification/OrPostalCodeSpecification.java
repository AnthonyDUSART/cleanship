package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;
import java.util.Objects;

public record OrPostalCodeSpecification(PostalCodeSpecification left, PostalCodeSpecification right)
    implements PostalCodeSpecification {

  public OrPostalCodeSpecification {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
  }

  public static OrPostalCodeSpecification of(
      PostalCodeSpecification left, PostalCodeSpecification right) {
    return new OrPostalCodeSpecification(left, right);
  }

  @Override
  public boolean isSatisfiedBy(PostalCode postalCode) {
    Objects.requireNonNull(postalCode);

    return left.isSatisfiedBy(postalCode) || right.isSatisfiedBy(postalCode);
  }
}
