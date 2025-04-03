Problem Definition:



Implement an order matching system for a stock exchange.



Features:

Traders place Buy and Sell orders for a stock indicating the price and quantity.

Each order gets entered into the exchange’s order-book and remains there until it is matched. Order matching is attempted whenever a new order is added.

The exchange follows a FirstInFirstOut Price-Time order-matching rule, which states that: “The first order in the order-book at a price level is the first order matched. All orders at the same price level are filled according to time priority”.



The exchange works like a market where lower selling prices and higher buying prices get priority.



A trade is executed when a buy price is greater than or equal to a sell price. The trade is recorded at the price of the sell order regardless of the price of the buy order.



Requirements:

1. Add/Register users to the stock exchange system.

2. Add stock to the stock exchange system.

3. Each user should have a stock holding list(stock, price, qty) & balance.

4. Users should be able to add balance to their account.

5. Users should be able to buy/sell multiple stocks.

6. Users should be able to check past successful trading.



Add-on Requirements:

1. Users should be able to edit(change buying/selling price & qty)/cancel order.



Test Cases:

Write a program that accepts orders as below input and writes trades as below output.



e.g. The following input (format:<order-id> <time> <stock> <buy/sell> <price> <qty>):



#1 09:45 Meesho sell 240.12 100

#2 09:46 Meesho sell 2437.5  90

#3 09:47 Meesho buy  238.10 110

#4 09:48 Meesho buy  237.80  10

#5 09:49 Meesho buy  237.80  40

#6 09:50 Meesho sell 236.00  50



Should produce the following output (format:<buy-order-id> <sell-price> <qty> <sell-order-id>):



#3 237.45 90 #2

#3 236.00 20 #6

#4 236.00 10 #6

#5 236.00 20 #6





Other Details:

1. Don't use any database or NoSQL store, use in-memory store for now.

2. Don't create any UI for the application.

3. Code should be demo-able. Write driver class for demo purpose, which will execute all commands at one
place in code and test cases.

4. Code should be extensible. Whenever applicable, use interfaces and contracts between different methods.

5. Work on the expected output first and then add good-to-have features of your own.