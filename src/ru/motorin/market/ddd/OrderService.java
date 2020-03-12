package ru.motorin.market.ddd;

import static ru.motorin.market.presentation.Currency.EUR;

public class OrderService {
    public String sberBankEquity() {
        return "SBER";
    }

    /**
     * - маскируем метод build заменяя на sendTo
     * - переносим часть бизнес логики по отправке ордера в доменный объект
     * в Order.sendTo или в Broker.atPrice
     */
    public void placeOrder1(Broker broker) {
        // Уход от необходимости вызова метода build
        Order.sellOrder()
                .symbol(sberBankEquity())
                .price(EUR(87))
                .quantity(10)
                .sendTo(broker);
    }


    /**
     * Что бы не засорять апи, нужно инкапсулировать изменяющуюся функциональность - стратегию покупки.
     * Несколько стратегий покупки - стратегии вынесены в отдельные лямбды
     * Стратегии определения размера позиции
     * Стратегии определения цены покупки
     */
    public void placeOrderByStrategies(Broker broker) {
        // купить максимум 10 по рыночной цене
        Order.buyOrder()
                .symbol(sberBankEquity())
                .price(EUR(PaymentStrategies.marketPrice()))
                .quantity(QuantityStrategies.atMax(10))
                .sendTo(broker);

        // купить минимум 100шт, когда цена ниже 87
        Order.sellOrder()
                .symbol(sberBankEquity())
                .price(EUR(QuantityStrategies.exact(87)))
                .quantity(QuantityStrategies.atLeast(100))
                .sendTo(broker);
    }
}
