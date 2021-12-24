package com.limited.time.deals.limitedtimedeals.service;

import com.limited.time.deals.limitedtimedeals.exceptions.InvalidDealException;
import com.limited.time.deals.limitedtimedeals.model.Deal;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DealInventory {
    @Getter
    private final Map<UUID, Deal> deals;

    public DealInventory() {
        this.deals = new HashMap<>();
    }

   public void addNewDeal(Deal deal){
        deals.put(deal.getId(), deal);
   }

    public Deal getDeal(UUID dealId) throws InvalidDealException {
        if(!deals.containsKey(dealId)) throw new InvalidDealException("Given Deal ID is invalid");
        return deals.get(dealId);

    }

    public void updateDeal(Deal deal) {
        //validate deal
        deals.put(deal.getId(), deal);
    }
}
