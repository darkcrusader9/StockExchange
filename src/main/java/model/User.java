package model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    public String userId;
    public double balance;
    public Map<String, Integer> stockHoldings;

    public User(String userId, double balance) {
        this.userId = userId;
        this.balance = balance;
        this.stockHoldings = new ConcurrentHashMap<>();
    }

    public void addStock(String stockSymbol, int quantity) {
        stockHoldings.put(stockSymbol, stockHoldings.getOrDefault(stockSymbol, 0) + quantity);
    }

    public boolean removeStock(String stockSymbol, int quantity) {
        int current = stockHoldings.getOrDefault(stockSymbol, 0);
        if (current >= quantity) {
            stockHoldings.put(stockSymbol, current - quantity);
            return true;
        }
        return false;
    }

    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }
}