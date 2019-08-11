package com.ubs.calculator.domain;

public interface TwoWayPrice {
    Instrument getInstrument();

    State getState();

    double getBidPrice();

    double getOfferAmount();

    double getOfferPrice();

    double getBidAmount();
}