package com.limited.time.deals.limitedtimedeals.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.limited.time.deals.limitedtimedeals.enums.DealStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Deal {

    private UUID id;
    private Item item;
    @Setter
    private DealStatus status;
    private AtomicInteger units;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date endDate;
    private double price;

    public Deal(AtomicInteger units, Date startDate, Date endDate, double price) {
        this.units = units;
        this.startDate = startDate;
        this.endDate = endDate;
        id = UUID.randomUUID();
        this.price = price;
    }

    public void decreaseUnit(){
        units.decrementAndGet();
    }

    public void updateDealStatus(){
        Date date = new Date();
        if(date.after(endDate)){
            status = DealStatus.ENDED;
        }
    }

}
