package controller;

import model.Order;
import model.OrderBook;
import model.Stock;
import model.User;
import service.OrderMatchingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockExchange {
    public Map<String, User> userMap;
    public Map<String, Stock> stockMap;
    public OrderBook orderBook;
    public OrderMatchingService matchingService;
    public Map<User, List<Order>> orderHistory;

    public StockExchange() {
        this.userMap = new HashMap<>();
        this.stockMap = new HashMap<>();
        this.orderBook = new OrderBook();
        this.orderHistory = new HashMap<>();
        this.matchingService = new OrderMatchingService(orderBook, orderHistory);
    }

    public void addUser(User user){
        userMap.put(user.userId, user);
    }

    public void addStock(Stock stock){
        stockMap.put(stock.symbol, stock);
    }

    public void placeOrder(Order order){
        orderBook.addOrder(order);
        matchingService.matchOrders(order.stock.symbol);
    }


}
