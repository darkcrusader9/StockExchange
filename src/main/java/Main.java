import controller.StockExchange;
import model.*;
import model.enums.OrderType;

public class Main {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();

        // Adding stocks
        Stock apple = new Stock("AAPL", 150);
        Stock google = new Stock("GOOGL", 2800);
        stockExchange.addStock(apple);
        stockExchange.addStock(google);

        // Adding users
        User user1 = new User("U1", 10000);
        User user2 = new User("U2", 10000);
        stockExchange.addUser(user1);
        stockExchange.addUser(user2);

        // Placing orders
        stockExchange.placeOrder(new Order("O1", OrderType.SELL, 150, 10, user1, apple));
        stockExchange.placeOrder(new Order("O2", OrderType.SELL, 100, 5, user2, apple));
        stockExchange.placeOrder(new Order("O3", OrderType.BUY, 155, 7, user2, apple));
        stockExchange.placeOrder(new Order("O4", OrderType.BUY, 160, 10, user2, apple));

        System.out.println("Trade execution completed.");
    }
}
