public class Main {
    public static void main(String[] args) {
        TradingSystem tradingSystem = new TradingSystem();
        StockExchange stockExchange = tradingSystem.stockExchange;

        // Adding stocks
        Stock apple = new Stock("AAPL", 150);
        stockExchange.addStock(apple);

        // Adding users
        User user1 = new User("U1", 10000);
        User user2 = new User("U2", 500);
        stockExchange.addUser(user1);
        stockExchange.addUser(user2);

        // Placing orders
        tradingSystem.placeOrder(new Order("O1", OrderType.SELL, 150, 10, user1, apple));
        tradingSystem.placeOrder(new Order("O2", OrderType.BUY, 150, 5, user2, apple));
        tradingSystem.placeOrder(new Order("O3", OrderType.BUY, 155, 5, user2, apple));

        // Allow time for matching to execute
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Trade execution completed.");
        tradingSystem.shutdown();
    }
}
