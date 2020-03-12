package ru.oz.fitnesspay.domain;

import lombok.Getter;
import org.joda.money.Money;

import java.util.UUID;

public class Ewallet {

    @Getter
    private UUID number;

    @Getter
    private Account account;

    @Getter
    private Money balance;

    public Money debit(Money money) {
        balance.plus(money);
        return balance;
    }

    public Money credit(Money money) {
        balance.minus(money);
        return balance;
    }

    public Money tranferTo(Ewallet to, Money money) {
        credit(money);
        to.debit(money);

        return getBalance();
    }
}
