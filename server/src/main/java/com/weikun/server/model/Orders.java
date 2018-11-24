package com.weikun.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Orders extends OrdersKey implements Serializable {
    private Date orderdate;

    private BigDecimal totalprice;

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }
}