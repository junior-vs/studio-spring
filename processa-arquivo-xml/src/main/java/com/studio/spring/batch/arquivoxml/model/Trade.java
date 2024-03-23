package com.studio.spring.batch.arquivoxml.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Trade {
    private  String isin;
    private  Long quantity;
    private  BigDecimal price;
    private  String customer;

    public Trade(String isin, Long quantity, BigDecimal price, String customer) {
        this.isin = isin;
        this.quantity = quantity;
        this.price = price;
        this.customer = customer;
    }

    public String isin() {
        return isin;
    }

    public Long quantity() {
        return quantity;
    }

    public BigDecimal price() {
        return price;
    }

    public String customer() {
        return customer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Trade) obj;
        return Objects.equals(this.isin, that.isin) &&
                Objects.equals(this.quantity, that.quantity) &&
                Objects.equals(this.price, that.price) &&
                Objects.equals(this.customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin, quantity, price, customer);
    }

    @Override
    public String toString() {
        return "Trade[" +
                "isin=" + isin + ", " +
                "quantity=" + quantity + ", " +
                "price=" + price + ", " +
                "customer=" + customer + ']';
    }

}
