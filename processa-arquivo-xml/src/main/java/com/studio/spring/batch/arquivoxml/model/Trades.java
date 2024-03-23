package com.studio.spring.batch.arquivoxml.model;

import java.util.List;
import java.util.Objects;

public final class Trades {
    private final List<Trade> trades;

    public Trades(List<Trade> trades) {
        this.trades = trades;
    }

    public List<Trade> trades() {
        return trades;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Trades) obj;
        return Objects.equals(this.trades, that.trades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trades);
    }

    @Override
    public String toString() {
        return "Trades[" +
                "trades=" + trades + ']';
    }

}
