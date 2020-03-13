package ru.motorin.market.presentation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String symbol;
    private Integer price;
    private int quantity;
    private Direction direction;

    enum Direction {
        BUY,
        SELL
    }

    @Deprecated
    public static Order buy(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.BUY);
    }

    @Deprecated
    public static Order sell(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.BUY);
    }

    @Builder(builderClassName = "DefaultBuilder", builderMethodName = "full", buildMethodName = "build")
    public static Order defaultBuilder(String symbol, Integer price, int quantity, Direction direction) {
        return new Order(symbol, price, quantity, direction);
    }

    @Builder(builderClassName = "BuyOrderBuilder", builderMethodName = "buyOrder", buildMethodName = "build")
    public static Order buyOrderBuilder(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.BUY);
    }

    @Builder(builderClassName = "SellOrderBuilder", builderMethodName = "sellOrder", buildMethodName = "build")
    public static Order sellOrderBuilder(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.SELL);
    }

    public static class BuyOrderBuilder extends DefaultBuilder {
        public void sendTo(Broker broker) {
            // Validates required fields
            // Validate.notBlank(super.name, "Order SYMBOL cannot be null or empty!");
            // Validate.notBlank(super.QUANTITY, "Order QUANTITY cannot be null or empty!");
            // Validate.notBlank(super.DIRECTION, "Order DIRECTION cannot be null or empty!");
            broker.addOrder(this.build());
        }

        public BuyOrderBuilder price(Currency currencedQuontity) {
            this.price(currencedQuontity.getAmount());
            return this;
        }
    }

    public static class SellOrderBuilder extends DefaultBuilder {
        public void sendTo(Broker broker) {
            // Validates required fields
            // Validate.notBlank(super.name, "Order SYMBOL cannot be null or empty!");
            // Validate.notBlank(super.QUANTITY, "Order QUANTITY cannot be null or empty!");
            // Validate.notBlank(super.DIRECTION, "Order DIRECTION cannot be null or empty!");
            broker.addOrder(this.build());
        }

        public SellOrderBuilder price(Currency currencedQuontity) {
            this.price(currencedQuontity.getAmount());
            return this;
        }
    }

}
