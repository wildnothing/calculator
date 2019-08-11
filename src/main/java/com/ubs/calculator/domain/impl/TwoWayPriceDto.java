package com.ubs.calculator.domain.impl;

import com.ubs.calculator.domain.Instrument;
import com.ubs.calculator.domain.State;
import com.ubs.calculator.domain.TwoWayPrice;

import java.io.Serializable;

public final class TwoWayPriceDto implements TwoWayPrice, Serializable {

    private static final long serialVersionUID = -1358295408827132798L;

    private final Instrument instrument;
    private final State state;
    private final double bidPrice;
    private final double offerAmount;
    private final double offerPrice;
    private final double bidAmount;

    public TwoWayPriceDto(Instrument instrument, State state, double bidPrice,
                          double offerAmount, double offerPrice, double bidAmount) {

        this.instrument = instrument;
        this.state = state;
        this.bidPrice = bidPrice;
        this.offerAmount = offerAmount;
        this.offerPrice = offerPrice;
        this.bidAmount = bidAmount;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public double getBidPrice() {
        return bidPrice;
    }

    @Override
    public double getOfferAmount() {
        return offerAmount;
    }

    @Override
    public double getOfferPrice() {
        return offerPrice;
    }

    @Override
    public double getBidAmount() {
        return bidAmount;
    }
}
