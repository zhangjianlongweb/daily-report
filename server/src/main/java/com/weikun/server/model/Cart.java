package com.weikun.server.model;

import java.io.Serializable;

public class Cart extends CartKey implements Serializable {
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}