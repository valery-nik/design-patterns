package ru.motorin.market.anemic;

public class Broker {

    private static final ThreadLocal<Order.DefaultBuilder> threadLocalScope = new ThreadLocal<>();

    /**
     * Разместить заявку на бирже
     *
     * @param order
     */
    public void placeOrder(Order order) {
        System.out.println("Заявка" + order + "отправлена на биржу");
    }

    /**
     * Терминальный метод реализующий запуск логики  выставления цены м
     * отравки ордера через брокер
     *
     * @param price
     */
    public void atPrice(int price) {
        placeOrder(threadLocalScope.get()
                .price(price)
                .build()
        );
    }

    public Broker buyOrder() {
        threadLocalScope.set(Order.buyOrder());
        return this;
    }

    public Broker sellOrder() {
        threadLocalScope.set(Order.buyOrder());
        return this;
    }

    public Broker symbol(String symbol) {
        threadLocalScope.get().symbol = symbol;
        return this;
    }

    public Broker quantity(int quantity) {
        threadLocalScope.get().quantity = quantity;
        return this;
    }
}
