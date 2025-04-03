package service;


import model.Order;
import model.OrderBook;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class OrderMatchingService {

    private final OrderBook orderBook;
    private final Lock lock;
    private final ExecutorService executorService;
    private final Map<User, List<Order>> userHistory;


    public OrderMatchingService(OrderBook orderBook, Map<User, List<Order>> userHistory) {
        this.orderBook = orderBook;
        this.lock = new ReentrantLock();
        this.executorService = Executors.newSingleThreadExecutor();
        this.userHistory = new HashMap<>();
    }

    public void matchOrders(String stockSymbol) {
        PriorityBlockingQueue<Order> buyOrders = orderBook.getBuyOrders(stockSymbol);
        PriorityBlockingQueue<Order> sellOrders = orderBook.getSellOrders(stockSymbol);

        while (!sellOrders.isEmpty() && !buyOrders.isEmpty()) {
            Order sell = sellOrders.peek();
            Order buy = buyOrders.peek();

            if (buy.price >= sell.price) {
                int tradedQuantity = Math.min(buy.quantity, sell.quantity);
                double tradePrice = sell.price;
                double totalCost = tradedQuantity * tradePrice;

                // Process the trade
                buy.user.balance -= totalCost;
                sell.user.balance += totalCost;
                buy.user.addStock(stockSymbol, tradedQuantity);
                sell.user.removeStock(stockSymbol, tradedQuantity);

                System.out.println("Matched: " + buy.orderId + " <--> " + sell.orderId +
                        " | Price: $" + tradePrice + " | Qty: " + tradedQuantity);

                //Add to history for both the users
                userHistory.computeIfAbsent(buy.user, k -> new ArrayList<>()).add(buy);
                userHistory.computeIfAbsent(sell.user, k -> new ArrayList<>()).add(sell);

                buy.quantity -= tradedQuantity;
                sell.quantity -= tradedQuantity;

                if (sell.quantity == 0) sellOrders.poll();
                if (buy.quantity == 0) buyOrders.poll();
            } else {
                break;
            }
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}






