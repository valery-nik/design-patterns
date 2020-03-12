package ru.motorin.market.presentation;

import java.util.function.ToIntFunction;

/**
 * Стратегии вычисления цены, которая выставляется в ордере
 */
public class PaymentStrategies {

//    public static ToDoubleFunction<Order> whenPriceLowerThan(int price) {
//        return (order) -> price;
//    }

    public static ToIntFunction<DepthOfMarket> whenPriceLowerThan(int price) {
        return (dom) -> price;
    }

    public static ToIntFunction<DepthOfMarket> marketPrice() {
        return (dom) -> dom.getCurrentMarketPrice();
    }
}
