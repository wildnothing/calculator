package com.ubs.calculator.domain.impl;

import com.ubs.calculator.domain.Instrument;
import com.ubs.calculator.domain.Market;
import com.ubs.calculator.domain.State;
import com.ubs.calculator.domain.TwoWayPrice;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarketUpdateDtoTest {

    @Test
    public void shouldTestGettersAndSetters() {

        TwoWayPrice twoWayPrice =
                new TwoWayPriceDto(Instrument.INSTRUMENT0, State.INDICATIVE,
                        1.0d, 2.0d, 3.0d, 4.0d);

        MarketUpdateDto marketUpdateDto =
                new MarketUpdateDto(Market.MARKET0, twoWayPrice);

        assertThat(marketUpdateDto.getMarket()).isEqualTo(Market.MARKET0);

        assertThat(twoWayPrice.getInstrument()).isEqualTo(Instrument.INSTRUMENT0);
        assertThat(twoWayPrice.getState()).isEqualTo(State.INDICATIVE);
        assertThat(twoWayPrice.getBidPrice()).isEqualTo(1.0d);
        assertThat(twoWayPrice.getOfferAmount()).isEqualTo(2.0d);
        assertThat(twoWayPrice.getOfferPrice()).isEqualTo(3.0d);
        assertThat(twoWayPrice.getBidAmount()).isEqualTo(4.0d);

        assertThat(marketUpdateDto.getTwoWayPrice()).isNotEqualTo(twoWayPrice);
    }
}