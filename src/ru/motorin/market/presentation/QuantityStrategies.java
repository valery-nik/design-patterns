package ru.motorin.market.presentation;

import java.util.function.ToIntFunction;

/**
 * Стратегии определения объема позиции
 */
public class QuantityStrategies {

    public static ToIntFunction<DepthOfMarket> atLeast(int leastQuantity) {
        return (dom) -> leastQuantity;
    }

    public static ToIntFunction<DepthOfMarket> atMax(int maxQuantity) {
        return (dom) -> maxQuantity;
    }

    public static ToIntFunction<DepthOfMarket> exact(int leastQuantity) {
        return (dom) -> leastQuantity;
    }

}
