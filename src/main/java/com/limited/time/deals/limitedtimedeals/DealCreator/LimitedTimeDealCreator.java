package com.limited.time.deals.limitedtimedeals.DealCreator;

import com.limited.time.deals.limitedtimedeals.service.DealInventory;
import com.limited.time.deals.limitedtimedeals.enums.DealStatus;
import com.limited.time.deals.limitedtimedeals.exceptions.InvalidDealException;
import com.limited.time.deals.limitedtimedeals.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitedTimeDealCreator implements DealCreator{

    @Autowired
    DealInventory dealInventory;

    @Override
    public Deal createDeal(Deal deal) throws InvalidDealException {
            //validate deal
        deal.setStatus(DealStatus.ACTIVE);
        dealInventory.addNewDeal(deal);

        return deal;
    }
}
