package com.rayvision.rpc.business.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;

public class Rate implements Serializable {
    private Integer id;

    private String currency;

    private BigDecimal rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}