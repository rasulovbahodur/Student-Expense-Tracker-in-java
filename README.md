# Student-Expense-Tracker-in-java
Student Expense Tracker – a Java-based console application for managing personal expenses, featuring CSV persistence, modular architecture, and dynamic spending summaries by category and month.

This is a simple Java console app for tracking personal expenses. It lets users add expenses, view them later, delete entries, and see basic spending totals. All data is saved to a CSV file, so it stays even after the app is closed.

## What it does

* Add an expense with amount, category, description, and date
* View a list of all saved expenses
* Delete an expense by selecting it
* See total spending and basic summaries
* Store data in a CSV file (no database)

## Project structure

```
src/
 ├── App.java            // Runs the app and handles user input
 ├── Expense.java        // Represents a single expense
 ├── ExpenseManager.java // Handles expense logic
 └── StorageHandler.java // Reads and writes CSV files

data/
 └── expenses.csv        // Saved expenses
```

## How to run

1. Open the project in VS Code or any Java IDE
2. Make sure Java is installed
3. Run `App.java`

## Notes

This project was built to practice Java fundamentals like object-oriented design, file handling, and clean program structure.
