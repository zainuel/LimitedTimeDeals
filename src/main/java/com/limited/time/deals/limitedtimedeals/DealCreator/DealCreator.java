package com.limited.time.deals.limitedtimedeals.DealCreator;

import com.limited.time.deals.limitedtimedeals.exceptions.InvalidDealException;
import com.limited.time.deals.limitedtimedeals.model.Deal;

public interface DealCreator {

    public Deal createDeal(Deal deal) throws InvalidDealException;
}
