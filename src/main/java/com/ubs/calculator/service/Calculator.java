package com.ubs.calculator.service;

import com.ubs.calculator.domain.MarketUpdate;
import com.ubs.calculator.domain.TwoWayPrice;

public interface Calculator {

    TwoWayPrice applyMarketUpdate(final MarketUpdate twoWayMarketPrice);
}

