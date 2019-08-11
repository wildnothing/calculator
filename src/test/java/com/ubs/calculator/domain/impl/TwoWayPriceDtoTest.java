package com.ubs.calculator.domain.impl;

import com.ubs.calculator.domain.Instrument;
import com.ubs.calculator.domain.State;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TwoWayPriceDtoTest {

    @Test
    public void shouldTestGettersAndSetters() {
        TwoWayPriceDto twoWayPriceDto = new TwoWayPriceDto(Instrument.INSTRUMENT0, State.FIRM,
                1.0d, 2.0d, 3.0d, 4.0d);

        assertThat(twoWayPriceDto.getInstrument()).isEqualTo(Instrument.INSTRUMENT0);
        assertThat(twoWayPriceDto.getState()).isEqualTo(State.FIRM);
        assertThat(twoWayPriceDto.getBidPrice()).isEqualTo(1.0d);
        assertThat(twoWayPriceDto.getOfferAmount()).isEqualTo(2.0d);
        assertThat(twoWayPriceDto.getOfferPrice()).isEqualTo(3.0d);
        assertThat(twoWayPriceDto.getBidAmount()).isEqualTo(4.0d);
    }
}