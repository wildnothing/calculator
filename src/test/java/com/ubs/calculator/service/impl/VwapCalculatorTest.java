package com.ubs.calculator.service.impl;

import com.ubs.calculator.domain.Instrument;
import com.ubs.calculator.domain.Market;
import com.ubs.calculator.domain.MarketData;
import com.ubs.calculator.domain.MarketUpdate;
import com.ubs.calculator.domain.State;
import com.ubs.calculator.domain.TwoWayPrice;
import com.ubs.calculator.domain.impl.MarketUpdateDto;
import com.ubs.calculator.domain.impl.TwoWayPriceDto;
import com.ubs.calculator.exception.VwapCalculatorException;
import org.agrona.collections.Int2ObjectHashMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VwapCalculatorTest {

    private final VwapCalculator vwapCalculator = new VwapCalculator(MarketData.get());

    @Test
    public void shouldCalculateVwapTwoWayPrice() {

        Int2ObjectHashMap<TwoWayPrice> instrumentMarkets = MarketData.get().get(Instrument.INSTRUMENT0);
        instrumentMarkets.put(Market.MARKET0.ordinal(), getTwoWayPrice(State.INDICATIVE, 1.0d, 1.0d, 1.0d, 1.0d));
        instrumentMarkets.put(Market.MARKET1.ordinal(), getTwoWayPrice(State.FIRM, 2.0d, 2.0d, 1.0d, 1.0d));

        TwoWayPrice twoWayPrice = vwapCalculator.applyMarketUpdate(getMarketUpdate(State.INDICATIVE));

        assertThat(twoWayPrice.getInstrument()).isEqualTo(Instrument.INSTRUMENT0);
        assertThat(twoWayPrice.getState()).isEqualTo(State.INDICATIVE);
        assertThat(twoWayPrice.getBidPrice()).isEqualTo(1.2d);
        assertThat(twoWayPrice.getOfferAmount()).isEqualTo(4.0d);
        assertThat(twoWayPrice.getOfferPrice()).isEqualTo(2.0d);
        assertThat(twoWayPrice.getBidAmount()).isEqualTo(5.0d);
    }

    @Test
    public void shouldCalculateFirmVwapTwoWayPrice() {

        Int2ObjectHashMap<TwoWayPrice> instrumentMarkets = MarketData.get().get(Instrument.INSTRUMENT0);
        instrumentMarkets.put(Market.MARKET0.ordinal(), getTwoWayPrice(State.INDICATIVE, 1.0d, 1.0d, 1.0d, 1.0d));
        instrumentMarkets.put(Market.MARKET1.ordinal(), getTwoWayPrice(State.FIRM, 2.0d, 2.0d, 1.0d, 1.0d));

        TwoWayPrice twoWayPrice = vwapCalculator.applyMarketUpdate(getMarketUpdate(State.FIRM));

        assertThat(twoWayPrice.getInstrument()).isEqualTo(Instrument.INSTRUMENT0);
        assertThat(twoWayPrice.getState()).isEqualTo(State.FIRM);
        assertThat(twoWayPrice.getBidPrice()).isEqualTo(1.2d);
        assertThat(twoWayPrice.getOfferAmount()).isEqualTo(4.0d);
        assertThat(twoWayPrice.getOfferPrice()).isEqualTo(2.0d);
        assertThat(twoWayPrice.getBidAmount()).isEqualTo(5.0d);
    }

    @Test(expected = VwapCalculatorException.class)
    public void shouldThrowExceptionWhenFailToCalculatePrice() {
        vwapCalculator.applyMarketUpdate(null);
    }

    private MarketUpdate getMarketUpdate(State state) {
        return new MarketUpdateDto(Market.MARKET0, getTwoWayPrice(state, 1.0d, 2.0d, 3.0d, 4.0d));
    }

    private TwoWayPrice getTwoWayPrice(State state, double bidPrice, double offerAmount,
                                       double offerPrice, double bidAmount) {

        return new TwoWayPriceDto(Instrument.INSTRUMENT0, state,
                bidPrice, offerAmount, offerPrice, bidAmount);
    }
}