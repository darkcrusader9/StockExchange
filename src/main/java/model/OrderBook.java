package model;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

class OrderBook {
    private final Map<String, PriorityBlockingQueue<Order>> buyOrders;
    private final Map<String, PriorityBlockingQueue<Order>> sellOrders;

    public OrderBook() {
        this.buyOrders = new ConcurrentHashMap<>();
        this.sellOrders = new ConcurrentHashMap<>();
    }

    public void addOrder(Order order) {
        String stockSymbol = order.stock.symbol;

        buyOrders.computeIfAbsent(stockSymbol, k -> new PriorityBlockingQueue<>(Comparator.naturalOrder()));
        sellOrders.computeIfAbsent(stockSymbol, k -> new PriorityBlockingQueue<>(Comparator.naturalOrder()));

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