package com.limited.time.deals.limitedtimedeals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//make this user and extend customer
@Getter
public class Customer {
    private String name;
    private String email;
    private UUID id;
    private final Set<UUID> dealsClaimed;

    public Customer(String email) {
        this.email = email;
        id =UUID.randomUUID();
        dealsClaimed = new HashSet<>();
    }

    public void updateDealsClaimed(UUID uuid){
        this.dealsClaimed.add(uuid);
    }


}
