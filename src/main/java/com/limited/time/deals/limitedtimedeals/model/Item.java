package com.limited.time.deals.limitedtimedeals.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Item {
    private UUID id;
    private String name;

    public Item(String name) {
        this.name = name;
        id = UUID.randomUUID();
    }
}
