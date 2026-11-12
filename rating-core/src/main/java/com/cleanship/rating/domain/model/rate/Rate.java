package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Shipment;
import java.util.Optional;

public sealed interface Rate permits FlatWeightRate {
  public Optional<Money> evaluatePrice(Shipment shipment);
}
