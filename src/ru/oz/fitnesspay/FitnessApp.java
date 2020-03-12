package ru.oz.fitnesspay;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import ru.oz.fitnesspay.domain.Account;
import ru.oz.fitnesspay.domain.Ewallet;

public class FitnessApp {

    public static void main(String[] args) {
        CurrencyUnit.registerCurrency("FIT", -1, 2, true);
        Account user1 = new Account();

        Ewallet ewallet = new Ewallet();
        ewallet.debit(Money.of(CurrencyUnit.of("FIT"), 100));

        System.out.println(ewallet.toString());
    }
}
