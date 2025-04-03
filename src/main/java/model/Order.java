package model;

class Order implements Comparable<Order> {
    String orderId;
    OrderType type;
    double price;
    int quantity;
    User user;
    Stock stock;
    long timestamp;

    public Order(String orderId, OrderType type, double price, int quantity, User user, Stock stock) {
        this.orderId = orderId;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
        this.stock = stock;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public int compareTo(Order other) {
        if (this.type == OrderType.BUY) {
            return Double.compare(other.price, this.price); // Buy: Max heap (higher prices first)
        } else {
            return Double.compare(this.price, other.price); // Sell: Min heap (lower prices first)
        }
    }

    @Override
    public String toString() {
        return orderId + " | " + type + " | $" + price + " | Qty: " + quantity;
    }
}