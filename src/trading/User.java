package trading;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private double balance;
    private Map<Stock, Integer> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost <= balance) {
            balance -= cost;
            portfolio.put(stock, portfolio.getOrDefault(stock, 0) + quantity);
            return true;
        }
        return false;
    }

    public boolean sellStock(Stock stock, int quantity) {
        if (portfolio.containsKey(stock) && portfolio.get(stock) >= quantity) {
            double earnings = stock.getPrice() * quantity;
            balance += earnings;
            portfolio.put(stock, portfolio.get(stock) - quantity);
            if (portfolio.get(stock) == 0) {
                portfolio.remove(stock);
            }
            return true;
        }
        return false;
    }

    public void viewPortfolio() {
        System.out.println("\n--- Portfolio of " + name + " ---");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
            return;
        }

        double totalValue = 0;
        for (Map.Entry<Stock, Integer> entry : portfolio.entrySet()) {
            Stock stock = entry.getKey();
            int qty = entry.getValue();
            double value = stock.getPrice() * qty;
            totalValue += value;
            System.out.println(stock.getSymbol() + " - " + qty + " shares - Value: $" + value);
        }
        System.out.println("Total Portfolio Value: $" + totalValue);
        System.out.println("Cash Balance: $" + balance);
    }
}
