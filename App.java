import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Summary");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1 -> manager.addExpense(sc);
                case 2 -> manager.viewExpenses(sc);
                case 3 -> manager.viewSummary();
                case 4 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
        System.out.println("Leaving application.");
    }
}
