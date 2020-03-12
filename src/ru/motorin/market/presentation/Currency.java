package ru.motorin.market.presentation;

import lombok.Getter;

public class Currency {

    @Getter
    private int amount;

    public static Currency EUR(int amount) {
        return new Currency(amount);
    }

    public static Currency USD(int amount) {
        return new Currency(amount);
    }

    public static Currency RUR(int amount) {
        return new Currency(amount);
    }

    public Currency(int amount) {
        this.amount = amount;
    }

}
