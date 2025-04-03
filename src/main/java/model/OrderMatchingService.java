import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class OrderMatchingService {

    private final OrderBook orderBook;
    private final Lock lock;
    private final ExecutorService executorService;

    public OrderMatchingSystem(OrderBook orderBook) {
        this.orderBook = orderBook;
        this.lock = new ReentrantLock();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void matchOrders(String stockSymbol) {
        executorService.execute(() -> {
            lock.lock();
            try {
                PriorityBlockingQueue<Order> buyOrders = orderBook.getBuyOrders(stockSymbol);
                PriorityBlockingQueue<Order> sellOrders = orderBook.getSellOrders(stockSymbol);

                while (!sellOrders.isEmpty() && !buyOrders.isEmpty()) {
                    Order sell = sellOrders.peek();
                    Order buy = buyOrders.peek();

                    if (buy.price >= sell.price) {
                        int tradedQuantity = Math.min(buy.quantity, sell.quantity);
                        double tradePrice = sell.price;
                        double totalCost = tradedQuantity * tradePrice;

                        // Validate User Balances & Holdings
                        if (!buy.user.hasSufficientBalance(totalCost)) {
                            System.out.println("Insufficient funds for " + buy.orderId);
                            buyOrders.poll(); // Remove invalid buy order
                            continue;
                        }
                        if (!sell.user.removeStock(stockSymbol, tradedQuantity)) {
                            System.out.println("Seller " + sell.user.userId + " does not have enough stocks!");
                            sellOrders.poll(); // Remove invalid sell order
                            continue;
                        }

                        // Process the trade
                        buy.user.balance -= totalCost;
                        sell.user.balance += totalCost;
                        buy.user.addStock(stockSymbol, tradedQuantity);

                        System.out.println("Matched: " + buy.orderId + " <--> " + sell.orderId +
                                " | Price: $" + tradePrice + " | Qty: " + tradedQuantity);

                        buy.quantity -= tradedQuantity;
                        sell.quantity -= tradedQuantity;

                        if (sell.quantity == 0) sellOrders.poll();
                        if (buy.quantity == 0) buyOrders.poll();
                    } else {
                        break;
                    }
                }
            } finally {
                lock.unlock();
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}






