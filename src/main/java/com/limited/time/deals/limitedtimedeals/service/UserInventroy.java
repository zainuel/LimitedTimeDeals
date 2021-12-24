package com.limited.time.deals.limitedtimedeals.service;

import com.limited.time.deals.limitedtimedeals.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInventroy {

    private final Map<String, Customer> customers;

    public UserInventroy(){
        customers = new HashMap<>();
    }

    public void addCustomer(Customer customer){
        customers.put(customer.getEmail(), customer);
    }

    public Customer getCustomer(String email){
        // if map doesn't contins
        return customers.get(email);
    }

    public boolean hasCustomer(String email){
        return customers.containsKey(email);
    }


}
