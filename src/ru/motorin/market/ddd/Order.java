package ru.motorin.market.ddd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.motorin.market.presentation.Currency;

import static ru.motorin.market.ddd.Order.Direction.BUY;
import static ru.motorin.market.ddd.Order.Direction.SELL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    enum Direction {
        BUY,
        SELL
    }

    private String symbol;
    private Integer price;
    private int quantity;
    private Direction direction;

    public static DefaultBuilder full() {
        return new DefaultBuilder();
    }

    public static DefaultBuilder sellOrder() {
        return new DefaultBuilder();
    }

    public static DefaultBuilder buyOrder() {
        return new DefaultBuilder();
    }

    public static Order defaultBuilder(String symbol, Integer price, int quantity, Direction direction) {
        return new Order(symbol, price, quantity, direction);
    }

    public static Order buyOrderBuilder(String symbol, Currency price, int quantity) {
        return new Order(symbol, price.getAmount(), quantity, BUY);
    }

    public static Order buyOrderBuilder(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, BUY);
    }

    public static Order sellOrderBuilder(String symbol, Currency price, int quantity) {
        return new Order(symbol, price.getAmount(), quantity, SELL);
    }

    public static Order sellOrderBuilder(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, SELL);
    }

    public static class CustomOrderBuilder extends DefaultBuilder {
        CustomOrderBuilder() {
        }

        public void sendTo(ru.motorin.market.presentation.Broker broker) {
            // Validates required fields
            // Validate.notBlank(super.name, "Order SYMBOL cannot be null or empty!");
            // Validate.notBlank(super.QUANTITY, "Order QUANTITY cannot be null or empty!");
            // Validate.notBlank(super.DIRECTION, "Order DIRECTION cannot be null or empty!");
            //broker.placeOrder(this.build());
        }

        public Order build() {
            return Order.buyOrderBuilder(symbol, price, quantity);
        }
    }

    public static class DefaultBuilder {
        String symbol;
        Integer price;
        int quantity;
        Direction direction;

        DefaultBuilder() {
        }

        public void sendTo(Broker broker) {
            // Validates required fields
            // Validate.notBlank(super.name, "Order SYMBOL cannot be null or empty!");
            // Validate.notBlank(super.QUANTITY, "Order QUANTITY cannot be null or empty!");
            // Validate.notBlank(super.DIRECTION, "Order DIRECTION cannot be null or empty!");
            broker.placeOrder(this.build());
        }

        public DefaultBuilder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public DefaultBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public DefaultBuilder price(Currency currencedQuontity) {
            this.price = currencedQuontity.getAmount();
            return this;
        }

        public DefaultBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public DefaultBuilder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Order build() {
            return Order.defaultBuilder(symbol, price, quantity, direction);
        }

        public String toString() {
            return "Order.DefaultBuilder(symbol=" + this.symbol + ", price=" + this.price + ", quantity=" + this.quantity + ", direction=" + this.direction + ")";
        }
    }
}
