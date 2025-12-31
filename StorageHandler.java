import java.io.*;
import java.util.*;

public class StorageHandler {

    private static final String FILE_PATH = "data/expenses.csv";

    public static void saveExpense(Expense e) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String safeDescription = e.getDescription().replace(",", ";");

            String line = String.format("%.2f,%s,%s,%s", e.getAmount(), e.getCategory(), safeDescription, e.getDate());

            out.println(line);
        } catch (IOException ex) {
            System.out.println("Error saving expense: " + ex.getMessage());
        }
    }

    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return expenses;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",", 4);
                if (parts.length < 4) continue;

                double amount = Double.parseDouble(parts[0]);
                String category = parts[1];
                String description = parts[2];
                String date = parts[3];

                Expense e = new Expense(amount, category, description, date);
                expenses.add(e);
            }
        } catch (IOException ex) {
            System.out.println("Error loading expenses: " + ex.getMessage());
        }
        return expenses;
    }
}