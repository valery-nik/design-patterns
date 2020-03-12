package ru.oz.grasp.informationexpert;

import lombok.Data;

import java.util.Date;
import java.util.List;

public class Register {
}

// https://www.sourcecodeexamples.net/2018/06/information-expert-grasp-pattern.html
class Sale {
    Date date;
    List<SalesLineItem> salesLineItems;

    /**
     * Плохая реализация - нарушение принципа information expert или закона дементры
     * класс(метод badGetTotal) знает о существовании ProductSpecification, т.е. изменения в этом классе могут поломать
     * и Sale#badGetTotal()
     *
     * @return
     */
    public double badGetTotal() {
        double total = 0;
        for (SalesLineItem li : salesLineItems) {
            total += li.getQuantity() * li.getProductSpecification().getPrice();
        }
        return total;
    }

    /**
     * Хорошая реализация
     * 1 -  low coupling
     * @return
     */
    public double getTotal() {
        double total = 0;
        for (SalesLineItem s : salesLineItems) {
            total += s.getSubTotal();
        }
        return total;
    }

}

@Data
class SalesLineItem {
    ProductSpecification productSpecification;
    int quantity;

    public double getSubTotal() {
        return this.getQuantity() * productSpecification.getPrice();
    }

}

@Data
class ProductSpecification {
    String description;
    int itemId;
    double price;
}
