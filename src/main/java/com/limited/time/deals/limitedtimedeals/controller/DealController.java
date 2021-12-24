package com.limited.time.deals.limitedtimedeals.controller;


import com.limited.time.deals.limitedtimedeals.model.Deal;
import com.limited.time.deals.limitedtimedeals.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("/v1/deals")
public class DealController {

    @Autowired
    DealService dealService;

    @PostMapping("/create")
    public ResponseEntity<Deal> create(@RequestBody Deal newDeal){
        Deal deal = dealService.createDeal(newDeal);
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }

    @PutMapping("/end/{dealId}")
    public ResponseEntity<Deal> endDeal(@PathVariable UUID dealId){
        Deal deal = dealService.endDeal(dealId);
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }

    @PutMapping("/claim/{dealId}/{customerId}") //todo:take request in object instead of param
    public ResponseEntity<Object> claimDeal(@PathVariable UUID dealId, @PathVariable String customerId){
           boolean isClaimed = dealService.claimDeal(dealId, customerId);
        return new ResponseEntity<>(isClaimed, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
