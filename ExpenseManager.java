import java.util.*;

public class ExpenseManager {

    private List<Expense> expenses;

    // Load expenses once at start
    public ExpenseManager() {
        expenses = StorageHandler.loadExpenses();
    }
    
    // Add Expense
    public void addExpense(Scanner sc) {
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        System.out.print("Date (MM/DD/YYYY): ");
        String date = sc.nextLine();

        Expense e = new Expense(amount, category, description, date);
        expenses.add(e);
        StorageHandler.saveExpense(e);

        System.out.println("Expense saved successfully.");
    }

    // View Expenses
    public void viewExpenses(Scanner sc) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        displayExpenses();

        System.out.println("1. Delete Expense");
        System.out.println("2. Reorder");
        System.out.println("0. Back");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            deleteExpense(sc);
            StorageHandler.rewriteAllExpenses(expenses);
        } else if (choice == 2) {
            reorderExpenses(sc);
        }
    }

    // Display Expenses
    private void displayExpenses() {
        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            System.out.printf(
                "%d) $%.2f | %s | %s | %s%n",
                i + 1,
                e.getAmount(),
                e.getCategory(),
                e.getDescription(),
                e.getDate()
            );
        }
    }

    // Delete Expense
    private void deleteExpense(Scanner sc) {
        System.out.print("Enter expense number to delete: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > expenses.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Expense removed = expenses.remove(index - 1);
        System.out.println("Deleted: " + removed.getDescription());
    }

    // Reorder
    private void reorderExpenses(Scanner sc) {
        System.out.println("Sort by:");
        System.out.println("1. Date");
        System.out.println("2. Alphabetically");
        System.out.println("3. Category");
        System.out.println("4. Amount");

        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 ->
                expenses.sort(Comparator.comparing(Expense::getDate));
            case 2 ->
                expenses.sort(Comparator.comparing(Expense::getDescription));
            case 3 ->
                expenses.sort(Comparator.comparing(Expense::getCategory));
            case 4 ->
                expenses.sort(Comparator.comparingDouble(Expense::getAmount));
            default ->
                System.out.println("Invalid option.");
        }
        displayExpenses();
    }

    // View Summary
    public void viewSummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to summarize.");
            return;
        }

        double total = 0;
        Map<String, Double> byCategory = new HashMap<>();
        Map<String, Double> byMonth = new HashMap<>();

        for (Expense e : expenses) {
            total += e.getAmount();

            // Category total
            byCategory.put(
                e.getCategory(),
                byCategory.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );

            // Month total (MM/YYYY)
            String month = e.getDate().substring(0, 2) + "/" + e.getDate().substring(6);
            byMonth.put(
                month,
                byMonth.getOrDefault(month, 0.0) + e.getAmount()
            );
        }

        System.out.printf("Total Spending: $%.2f%n", total);

        System.out.println("\nSpending by Category:");
        for (String cat : byCategory.keySet()) {
            System.out.printf("- %s: $%.2f%n", cat, byCategory.get(cat));
        }

        System.out.println("\nMonthly Totals:");
        for (String m : byMonth.keySet()) {
            System.out.printf("- %s: $%.2f%n", m, byMonth.get(m));
        }
    }
}
