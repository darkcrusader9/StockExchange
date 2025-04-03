package model;

import model.enums.OrderType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class OrderBook {
    private final Map<String, PriorityBlockingQueue<Order>> buyOrders;
    private final Map<String, PriorityBlockingQueue<Order>> sellOrders;

    public OrderBook() {
        this.buyOrders = new ConcurrentHashMap<>();
        this.sellOrders = new ConcurrentHashMap<>();
    }

    public void addOrder(Order order) {
        String stockSymbol = order.stock.symbol;

        buyOrders.computeIfAbsent(stockSymbol, k -> new PriorityBlockingQueue<>());
        sellOrders.computeIfAbsent(stockSymbol, k -> new PriorityBlockingQueue<>());

        if (order.type == OrderType.BUY) {
            buyOrders.get(stockSymbol).offer(order);
        } else {
            sellOrders.get(stockSymbol).offer(order);
        }
    }

    public PriorityBlockingQueue<Order> getBuyOrders(String stockSymbol) {
        return buyOrders.getOrDefault(stockSymbol, new PriorityBlockingQueue<>());
    }

    public PriorityBlockingQueue<Order> getSellOrders(String stockSymbol) {
        return sellOrders.getOrDefault(stockSymbol, new PriorityBlockingQueue<>());
    }
}