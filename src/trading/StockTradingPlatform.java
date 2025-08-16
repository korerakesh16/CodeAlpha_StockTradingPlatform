package trading;

import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {
	public static final String CURRENCY = "₹";

    private ArrayList<Stock> marketStocks = new ArrayList<>();
    private User user;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StockTradingPlatform platform = new StockTradingPlatform();
        platform.run();
    }

    public void run() {
        System.out.println("===== Welcome to Stock Trading Platform =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        user = new User(name, 10000); 

        
        marketStocks.add(new Stock("AAPL", "Apple", 150));
        marketStocks.add(new Stock("GOOGL", "Google", 2800));
        marketStocks.add(new Stock("TSLA", "Tesla", 700));
        marketStocks.add(new Stock("AMZN", "Amazon", 3300));

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. View Market Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMarket();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    user.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting... Thanks for trading!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void viewMarket() {
        System.out.println("\n--- Available Market Stocks ---");
        for (int i = 0; i < marketStocks.size(); i++) {
            System.out.println((i + 1) + ". " + marketStocks.get(i));
        }
    }

    private void buyStock() {
        viewMarket();
        System.out.print("Enter stock number to buy: ");
        int choice = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();

        if (choice > 0 && choice <= marketStocks.size()) {
            Stock stock = marketStocks.get(choice - 1);
            if (user.buyStock(stock, qty)) {
                System.out.println("Bought " + qty + " shares of " + stock.getSymbol());
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Invalid stock selection!");
        }
    }

    private void sellStock() {
        viewMarket();
        System.out.print("Enter stock number to sell: ");
        int choice = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();

        if (choice > 0 && choice <= marketStocks.size()) {
            Stock stock = marketStocks.get(choice - 1);
            if (user.sellStock(stock, qty)) {
                System.out.println("Sold " + qty + " shares of " + stock.getSymbol());
            } else {
                System.out.println("You don’t own enough shares!");
            }
        } else {
            System.out.println("Invalid stock selection!");
        }
    }
}
