
import java.util.*;

class Expense {
    int id;
    String category;
    double amount;
    String date;

    Expense(int id, String category, double amount, String date) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    void display() {
        System.out.printf("%-5d %-15s %-12.2f %-12s%n",
                id, category, amount, date);
    }
}

public class DailyExpenseAnalyzer {

    static ArrayList<Expense> expenses = new ArrayList<>();

    // Add Expense
    static void addExpense(Scanner sc) {
        System.out.print("Enter Expense ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        expenses.add(new Expense(id, category, amount, date));

        System.out.println("Expense added successfully!");
    }

    // Display All Expenses
    static void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.printf("%-5s %-15s %-12s %-12s%n",
                "ID", "Category", "Amount", "Date");

        for (Expense e : expenses) {
            e.display();
        }
    }

    // Search Expense by Category
    static void searchExpense(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter category to search: ");
        String key = sc.nextLine();

        boolean found = false;

        for (Expense e : expenses) {
            if (e.category.equalsIgnoreCase(key)) {
                e.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Expense not found!");
        }
    }

    // Sort in Ascending Order
    static void sortAscending() {
        expenses.sort(Comparator.comparingDouble(e -> e.amount));

        System.out.println("Expenses sorted by amount (Low to High):");
        displayExpenses();
    }

    // Sort in Descending Order
    static void sortDescending() {
        expenses.sort(
            Comparator.comparingDouble((Expense e) -> e.amount)
                      .reversed()
        );

        System.out.println("Expenses sorted by amount (High to Low):");
        displayExpenses();
    }

    // Calculate Total Expenses
    static void calculateTotal() {
        double total = 0;

        for (Expense e : expenses) {
            total += e.amount;
        }

        System.out.println("Total Expenses: Rs. " + total);
    }

    // Find Highest and Lowest Expense
    static void findHighestLowest() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        Expense highest = expenses.get(0);
        Expense lowest = expenses.get(0);

        for (Expense e : expenses) {
            if (e.amount > highest.amount) {
                highest = e;
            }

            if (e.amount < lowest.amount) {
                lowest = e;
            }
        }

        System.out.println("Highest Expense:");
        highest.display();

        System.out.println("Lowest Expense:");
        lowest.display();
    }

    // Search Expenses Greater Than Given Amount
    static void searchByAmount(Scanner sc) {
        System.out.print("Enter minimum amount: ");
        double amount = sc.nextDouble();

        boolean found = false;

        for (Expense e : expenses) {
            if (e.amount > amount) {
                e.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found above this amount.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== DAILY EXPENSE ANALYZER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display All Expenses");
            System.out.println("3. Search Expense by Category");
            System.out.println("4. Sort Expenses (Low to High)");
            System.out.println("5. Sort Expenses (High to Low)");
            System.out.println("6. Calculate Total Expenses");
            System.out.println("7. Find Highest and Lowest Expense");
            System.out.println("8. Search Expenses by Amount");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addExpense(sc);
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    searchExpense(sc);
                    break;

                case 4:
                    sortAscending();
                    break;

                case 5:
                    sortDescending();
                    break;

                case 6:
                    calculateTotal();
                    break;

                case 7:
                    findHighestLowest();
                    break;

                case 8:
                    searchByAmount(sc);
                    break;

                case 9:
                    System.out.println("Thank you for using Daily Expense Analyzer!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 9);

        sc.close();
    }
}
