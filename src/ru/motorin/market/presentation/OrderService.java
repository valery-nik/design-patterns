package ru.motorin.market.presentation;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.motorin.market.presentation.Currency.EUR;
import static ru.motorin.market.presentation.Order.Direction.BUY;

public class OrderService {
    public String sberBankEquity() {
        return "SBER";
    }

    public void createOrder(Broker broker) {
        ru.motorin.market.presentation.Order order = new Order(); // !!!
        order.setSymbol(sberBankEquity());
        order.setPrice(87); // !!!
        order.setQuantity(10); // !!!
        order.setDirection(BUY);

        broker.addOrder(order);
    }

    /**
     * Переход на immutable доменный объект
     */
    public void createOrder2(Broker broker) {
        Order order = new Order(sberBankEquity(), 87, 10, BUY);

        broker.addOrder(order);
    }

    /**
     * Инициализация через информативный фабричный метод (уход от ноунейм конструктора)
     */
    public void createOrder3(Broker broker) {
        broker.addOrder(ru.motorin.market.presentation.Order.buy(sberBankEquity(), 87, 10));
    }

    /**
     * Переход на именнованную инициализацию полей через builder (fluent api)
     */
    public void createOrder4(Broker broker) {
        broker.addOrder(Order.buyOrder()
                .price(87)
                .quantity(10)
                .build());
    }

    public void createOrder4_1(Broker broker) {
        broker.addOrder(Order.buyOrder()
                .price(EUR(87))
                .quantity(10)
                .build());
    }

    /**
     * - маскируем метод build заменяя на sendTo
     * - переносим часть бизнес логики по отправке ордера в доменный объект
     *   в Order.sendTo или в Broker.atPrice
     */
    public void createOrder5(Broker broker) {

        // Уход от необходимости вызова метода build
        Order.sellOrder()
                .symbol(sberBankEquity())
                .quantity(10)
                .price(EUR(87))
              //  .price(87)
                .sendTo(broker);

        // или так
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(10)
                .atPrice(87);

    }

    /**
     * Несколько стратегий покупки. Пример 3-ёх стратегий - говнокод в действии.
     * В итоге к api Broker добавили 4-ре новых метода, и по факту засорили апи у класса broker
     * Потом будут еще подобные "улучшения".
     */
    public void createOrder6(Broker broker) {

        Stream<ru.motorin.market.presentation.Order> orderStream = Stream.empty();
        orderStream.collect(Collectors.toSet());

        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(10)
                .atPrice(87); // купить 10 штук по цене 87

        // или так
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantityAtMax(10)
                .atMarketPrice(); // купить максимум 10 по рыночной цене

        // или так
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantityAtLeast(100)
                .whenPriceLowerThan(87); // Купить минимум 100шт, когда цена ниже 87

    }

    /**
     * Что бы не засорять апи, нужно инкапсулировать изменяющуюся функциональность - стратегию покупки.
     * Несколько стратегий покупки - стратегии вынесены в отдельные лямбды
     * Стратегии определения размера позиции
     * Стратегии определения цены покупки
     */
    public void createOrder7(Broker broker) {

        Stream<ru.motorin.market.presentation.Order> orderStream = Stream.empty();
        orderStream.collect(Collectors.toSet());

        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(10)
                .atPrice(87); // купить 10 штук по цене 87

        // или так
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(QuantityStrategies.atMax(10))
                .atPrice(PaymentStrategies.marketPrice()); // купить максимум 10 по рыночной цене

        // или так
        broker.buyOrder()
                .symbol(sberBankEquity())
                .quantity(QuantityStrategies.atLeast(100))
                .atPrice(PaymentStrategies.whenPriceLowerThan(87)); // Купить минимум 100шт, когда цена ниже 87

    }
}
