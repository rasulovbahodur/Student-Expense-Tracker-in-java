public class Expense {
    private double amount;
    private String category;
    private String date;
    private String description;

    public Expense(double amount, String category, String description, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;        
        this.description = description;
    }

    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
}
