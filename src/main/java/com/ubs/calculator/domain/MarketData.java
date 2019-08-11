package com.ubs.calculator.domain;

import org.agrona.collections.Int2ObjectHashMap;

import java.util.EnumMap;

/**
 * Initialising market data.
 *
 * We're assuming there will always be these instruments and that the Instrument enum will never
 * change the order of its instruments.
 *
 * Not initialising markets because we're going to assume instruments might not be traded in all markets.
 *
 * The inner map representing market data for each instrument implements linear probing to eradicate collisions.
 */
public class MarketData {

    private static final EnumMap<Instrument, Int2ObjectHashMap<TwoWayPrice>> MARKET_DATA;

    static {
        MARKET_DATA = new EnumMap<>(Instrument.class);
        MARKET_DATA.put(Instrument.INSTRUMENT0, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT1, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT2, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT3, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT4, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT5, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT6, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT7, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT8, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT9, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT10, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT11, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT12, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT13, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT14, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT15, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT16, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT17, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT18, new Int2ObjectHashMap<>(50, 0.75f));
        MARKET_DATA.put(Instrument.INSTRUMENT19, new Int2ObjectHashMap<>(50, 0.75f));
    }

    public static EnumMap<Instrument, Int2ObjectHashMap<TwoWayPrice>> get() {
        return MARKET_DATA;
    }
}
