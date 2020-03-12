package ru.motorin.market.anemic;

/**
 * Стратегии вычисления цены, которая выставляется в ордере
 */
public class PaymentStrategies {

    /**
     * Текущая цена, но не больше {@link price}
     *
     * @param price
     * @return
     */
    public static Integer whenPriceLowerThan(int price) {
        return 0;
    }

    /**
     * Текущая рыночная цена
     *
     * @return
     */
    public static Integer marketPrice() {
        return 0;
    }
}
