package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.location.PostalCode;
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

  public default PostalCodeSpecification not() {
    return NotPostalCodeSpecification.of(this);
  }

  public static PostalCodeSpecification startsWith(String prefix) {
    Objects.requireNonNull(prefix);

    return StartsWithMatchingPostalCodeSpecification.of(prefix);
  }

  public static PostalCodeSpecification endsWith(String suffix) {
    Objects.requireNonNull(suffix);

    return EndsWithMatchingPostalCodeSpecification.of(suffix);
  }

  public static PostalCodeSpecification contains(String sequence) {
    Objects.requireNonNull(sequence);

    return ContainsMatchingPostalCodeSpecification.of(sequence);
  }

  public static PostalCodeSpecification isEqualTo(String value) {
    Objects.requireNonNull(value);

    return EqualsMatchingPostalCodeSpecification.of(value);
  }

  public static PostalCodeSpecification any() {
    return AnyPostalCodeSpecification.of();
  }
}
