package ru.motorin.market.ddd;

/**
 * Стратегии вычисления цены, которая выставляется в ордере
 */
public class PaymentStrategies {

//    public static ToDoubleFunction<Order> whenPriceLowerThan(int price) {
//        return (order) -> price;
//    }

//    Integer whenPriceLowerThan(int price) {
//        return (dom) -> price;
//    }

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
