package com.ubs.calculator.domain.impl;

import com.ubs.calculator.domain.Market;
import com.ubs.calculator.domain.MarketUpdate;
import com.ubs.calculator.domain.TwoWayPrice;

import java.io.Serializable;

public final class MarketUpdateDto implements MarketUpdate, Serializable {

    private static final long serialVersionUID = -8672024856712438689L;

    private final Market market;
    private final TwoWayPrice twoWayPrice;

    public MarketUpdateDto(Market market, TwoWayPrice twoWayPrice) {

        this.market = market;
        this.twoWayPrice = new TwoWayPriceDto(
                twoWayPrice.getInstrument(), twoWayPrice.getState(),
                twoWayPrice.getBidPrice(),
                twoWayPrice.getOfferAmount(),
                twoWayPrice.getOfferPrice(),
                twoWayPrice.getBidAmount()
        );
    }

    @Override
    public Market getMarket() {
        return market;
    }

    @Override
    public TwoWayPrice getTwoWayPrice() {
        return twoWayPrice;
    }
}
