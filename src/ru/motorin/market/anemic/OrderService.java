package ru.motorin.market.anemic;

public class OrderService {
    public String sberBankEquity() {
        return "SBER";
    }

    /**
     * Что бы не засорять апи, нужно инкапсулировать изменяющуюся функциональность - стратегию покупки.
     * Несколько стратегий покупки - стратегии вынесены в отдельные лямбды
     * Стратегии определения размера позиции
     * Стратегии определения цены покупки
     */
    @SuppressWarnings("Duplicates")
    public void placeOrderByStrategies(Broker broker) {
        // купить 10 штук по цене 87
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(10)
                .atPrice(87); // купить 10 штук по цене 87

        // купить максимум 10 по рыночной цене
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(QuantityStrategies.atMax(10))
                .atPrice(PaymentStrategies.marketPrice()); // купить максимум 10 по рыночной цене

        // купить минимум 100шт, когда цена ниже 87
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(QuantityStrategies.atLeast(100))
                .atPrice(PaymentStrategies.whenPriceLowerThan(87)); // Купить минимум 100шт, когда цена ниже 87
    }
}
