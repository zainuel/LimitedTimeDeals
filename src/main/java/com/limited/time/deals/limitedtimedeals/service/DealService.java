package com.limited.time.deals.limitedtimedeals.service;

import com.limited.time.deals.limitedtimedeals.DealCreator.DealCreator;
import com.limited.time.deals.limitedtimedeals.enums.DealStatus;
import com.limited.time.deals.limitedtimedeals.exceptions.InvalidDealException;
import com.limited.time.deals.limitedtimedeals.model.Customer;
import com.limited.time.deals.limitedtimedeals.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DealService {

    @Autowired
    DealCreator dealCreator;

    @Autowired
    DealInventory dealInventory;

    @Autowired
    UserInventroy userInventroy;

    public Deal createDeal(Deal newDeal){
        return dealCreator.createDeal(newDeal);
    }
    // handle exception;
    public Deal endDeal(UUID dealId) throws InvalidDealException  {
       Deal deal =  dealInventory.getDeal(dealId);
       deal.setStatus(DealStatus.ENDED);
       dealInventory.updateDeal(deal);
       return deal;
    }

    //todo: make it synchronized to handle concurrency
    public boolean claimDeal(UUID dealId, String userId){
        Deal deal =  dealInventory.getDeal(dealId);
        deal.updateDealStatus();

        if(!userInventroy.hasCustomer(userId)){
            createCustomer(userId);
        }
        Customer customer = userInventroy.getCustomer(userId);
        if(!canDealBeClaimed(deal, customer)){
           // throw exception;
            return false;
        }

        deal.decreaseUnit();
        if(areDealItemsOutOfStock(deal)){
            deal.setStatus(DealStatus.CLAIMED);
        }
        dealInventory.updateDeal(deal);
        customer.updateDealsClaimed(dealId);
        return true;
    }

    private boolean areDealItemsOutOfStock(Deal deal) {
        return deal.getUnits().intValue() == 0;
    }


    private void createCustomer(String userId) {
        Customer customer = new Customer(userId);
        userInventroy.addCustomer(customer);
    }

    //todo: move this to validator class
    private boolean canDealBeClaimed(Deal deal){
        return (deal.getStatus() == DealStatus.ACTIVE); // status is active
    }

    //todo: move this to validator class
    private boolean canDealBeClaimed(Deal deal, Customer customer){
        return canDealBeClaimed(deal) && !customer.getDealsClaimed().contains(deal.getId());
    }

}
