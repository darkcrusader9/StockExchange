package model;

import java.util.Map;

public class StockExchange {
    Map<String, User> userMap;
    Map<String, Stock> stockMap;



    public StockExchange(Map<String, User> userMap, Map<String, Stock> stockMap) {
        this.userMap = userMap;
        this.stockMap = stockMap;
    }
}
