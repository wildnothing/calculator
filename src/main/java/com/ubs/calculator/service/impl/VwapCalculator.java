package com.ubs.calculator.service.impl;

import com.ubs.calculator.domain.Instrument;
import com.ubs.calculator.domain.MarketUpdate;
import com.ubs.calculator.domain.State;
import com.ubs.calculator.domain.TwoWayPrice;
import com.ubs.calculator.domain.impl.TwoWayPriceDto;
import com.ubs.calculator.exception.VwapCalculatorException;
import com.ubs.calculator.service.Calculator;
import org.agrona.collections.Int2ObjectHashMap;

import java.util.EnumMap;

public class VwapCalculator implements Calculator {

    private final EnumMap<Instrument, Int2ObjectHashMap<TwoWayPrice>> marketData;

    public VwapCalculator(EnumMap<Instrument, Int2ObjectHashMap<TwoWayPrice>> marketData) {
        this.marketData = marketData;
    }

    @Override
    public TwoWayPrice applyMarketUpdate(final MarketUpdate twoWayMarketPrice) {

        try {
            Int2ObjectHashMap<TwoWayPrice> instrumentMarketData = marketData
                    .get(twoWayMarketPrice.getTwoWayPrice().getInstrument());

            instrumentMarketData.merge(twoWayMarketPrice.getMarket().ordinal(),
                    twoWayMarketPrice.getTwoWayPrice(),
                    (oldPrice, newPrice) -> newPrice);

            return calculateTwoWayPrice(twoWayMarketPrice.getTwoWayPrice().getInstrument(),
                    instrumentMarketData);
        } catch(Exception e) {
            throw new VwapCalculatorException("Unable to calculate VWAP two-way price", e);
        }
    }

    // No functions or streams here, just a 1-pass iterative approach.
    // Quick, cheap, albeit verbose.
    private TwoWayPrice calculateTwoWayPrice(Instrument instrument,
                                             Int2ObjectHashMap<TwoWayPrice> instrumentsMarket) {

        double sumBidPriceAndAmount = 0.0d;
        double sumBidAmount = 0.0d;
        double sumOfferPriceAndAmount = 0.0d;
        double sumOfferAmount = 0.0d;
        boolean indicative = false;

        for (TwoWayPrice twoWayPrice : instrumentsMarket.values()) {

            if (twoWayPrice.getState().equals(State.INDICATIVE)) {
                indicative = true;
            }

            sumBidPriceAndAmount += twoWayPrice.getBidPrice() * twoWayPrice.getBidAmount();
            sumBidAmount += twoWayPrice.getBidAmount();
            sumOfferPriceAndAmount += twoWayPrice.getOfferPrice() * twoWayPrice.getOfferAmount();
            sumOfferAmount += twoWayPrice.getOfferAmount();
        }

        return new TwoWayPriceDto(instrument, indicative ? State.INDICATIVE : State.FIRM,
                sumBidPriceAndAmount/sumBidAmount, sumOfferAmount,
                sumOfferPriceAndAmount/sumOfferAmount, sumBidAmount);

    }
}
