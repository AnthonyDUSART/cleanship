package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.PostalCode;

public interface PostalCodeSpecification {
  public boolean isSatisfiedBy(PostalCode postalCode);
}
