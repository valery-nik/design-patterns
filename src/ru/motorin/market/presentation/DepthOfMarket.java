package ru.motorin.market.presentation;

import lombok.Data;

@Data
public class DepthOfMarket {

    private static volatile DepthOfMarket instance;

    private DepthOfMarket() {

    }

    public DepthOfMarket getSberDepthOfMarketInstance() {
        DepthOfMarket local = instance;
        if (local == null)
            synchronized (instance) {
                if (instance == null) {
                    local = new DepthOfMarket();
                }
            }

        return local;
    }

    int getCurrentMarketPrice() {
        return 555;
    }
}
