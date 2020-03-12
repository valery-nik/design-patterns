package ru.motorin.market.ddd;

public interface Broker {

    /**
     * Разместить заявку на бирже
     *
     * @param order
     */
    void placeOrder(Order order);

    /**
     * Текущая рыночна цена
     *
     * @return
     */
    Integer getCurrentPrice();
}
