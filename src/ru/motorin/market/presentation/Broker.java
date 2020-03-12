package ru.motorin.market.presentation;

import java.util.function.ToIntFunction;

public class Broker  {
    DepthOfMarket mom;

    /**
     * Отправить ордер
     * @param order
     */
    public void addOrder(Order order) {
        System.out.println("Some sending logic...");
    }

    private static final ThreadLocal<Order.DefaultBuilder> threadLocalScope = new ThreadLocal<>();

    public Broker sellOrder() {
        threadLocalScope.set(Order.sellOrder());
        return this;
    }

    public Broker buyOrder() {
        threadLocalScope.set(Order.buyOrder());
        return this;
    }

    public Broker symbol(String symbol) {
        threadLocalScope.get().symbol(symbol);
        return this;
    }

    public Broker quantity(int quantity) {
        threadLocalScope.get().quantity(quantity);
        return this;
    }

    public Broker quantity(ToIntFunction<DepthOfMarket> quantityStrategy ) {
        threadLocalScope.get().quantity(quantityStrategy.applyAsInt(mom));

        return this;
    }

    /**
     * Терминальный метод реализующий запуск логики выставления цены и
     *  отравки ордера через брокер
     * @param paymentStrategy
     */
    public void atPrice(ToIntFunction<DepthOfMarket> paymentStrategy) {
        threadLocalScope.get().price(paymentStrategy.applyAsInt(mom));

        addOrder(threadLocalScope.get().execute());
    }

    /**
     * Терминальный метод реализующий запуск логики отравки ордера через брокер
     * @param price
     */
    public void atPrice(int price) {
        addOrder(threadLocalScope.get().price(price).execute());
    }


    /**
     * Набор мусорных методов!
     */

    /**
     * Мусорный терминальный метод - засоряем api broker кучей методов
     */
    @Deprecated
    public void atMarketPrice() {
        addOrder(threadLocalScope.get().execute());
    }

    /**
     * Мусорный терминальный метод - засоряем api broker кучей методов
     */
    @Deprecated
    public void whenPriceLowerThan(int price) {
        addOrder(threadLocalScope.get().execute());
    }

    /**
     * Мусорный метод - засоряем api broker кучей методов
     */
    @Deprecated
    public Broker quantityAtMax(int i) {
        return this;
    }

    /**
     * Мусорный метод - засоряем api broker кучей методов
     */
    @Deprecated
    public Broker quantityAtLeast(int i) {
        return this;
    }
}
