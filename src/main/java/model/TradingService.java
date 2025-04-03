package model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradingService {
        private final OrderBook orderBook;
        private final OrderMatchingService matchingSystem;
        private final ExecutorService executor;
        public final StockExchange stockExchange;

        public TradingSystem() {
            this.orderBook = new OrderBook();
            this.matchingSystem = new OrderMatchingService(orderBook);
            this.executor = Executors.newFixedThreadPool(4);
            this.stockExchange = new StockExchange();
        }

        public void placeOrder(Order order) {
            executor.execute(() -> {
                orderBook.addOrder(order);
                matchingSystem.matchOrders(order.stock.symbol);
            });
        }

        public void shutdown() {
            executor.shutdown();
            matchingSystem.shutdown();
        }
}
