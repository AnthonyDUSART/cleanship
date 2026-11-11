package com.cleanship.rating.domain.model.specification;

import com.cleanship.rating.domain.model.Shipment;

public interface PostalCodeSpecification {
  public boolean isSatisfiedBy(Shipment shipment);

  public PostalCodeSpecification and(PostalCodeSpecification postalCodeSpecification);

  public PostalCodeSpecification or(PostalCodeSpecification postalCodeSpecification);

  public PostalCodeSpecification not();
}
