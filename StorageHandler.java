import java.io.*;
import java.util.*;

public class StorageHandler {
    private static final String FILE_PATH = "data/expenses.csv";

    // Load expenses from file
    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",", 4);
                if (parts.length < 4) continue;

                double amount = Double.parseDouble(parts[0]);
                String category = parts[1];
                String description = parts[2];
                String date = parts[3];

                expenses.add(new Expense(amount, category, description, date));
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses.");
        }
        return expenses;
    }

    // Save a single expense
    public static void saveExpense(Expense e) {
        ensureFileExists();

        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String safeDescription = e.getDescription().replace(",", ";");
            out.printf(
                "%.2f,%s,%s,%s%n",
                e.getAmount(),
                e.getCategory(),
                safeDescription,
                e.getDate()
            );
        } catch (IOException ex) {
            System.out.println("Error saving expense.");
        }
    }

    // Rewrite all expenses
    public static void rewriteAllExpenses(List<Expense> expenses) {
        ensureFileExists();

        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            out.println("amount,category,description,date");

            for (Expense e : expenses) {
                String safeDescription = e.getDescription().replace(",", ";");
                out.printf(
                    "%.2f,%s,%s,%s%n",
                    e.getAmount(),
                    e.getCategory(),
                    safeDescription,
                    e.getDate()
                );
            }
        } catch (IOException ex) {
            System.out.println("Error rewriting file.");
        }
    }

    // Make sure file and directories exist
    private static void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                PrintWriter out = new PrintWriter(file);
                out.println("amount,category,description,date");
                out.close();
            }
        } catch (IOException ignored) {}
    }
}
