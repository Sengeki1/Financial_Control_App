# Financial Control App

This app allows users to manage their personal finances by providing an interface to view, register, and manage financial transactions. It also includes performance charts, daily notifications to remind users to record their transactions, and a pie chart to visualize the financial distribution of transactions.

<p align="center">
  <img src="https://github.com/user-attachments/assets/99e87787-fd3b-4d54-b4bc-47839a9941a7" width="300" height="650" />
</p>

## Features

### 1. Main Screen
- **Financial Summary**:
  - **Current Balance**: Displays the difference between Income and Expenses.
  - **Total Income**: Sum of all registered incomes.
  - **Total Expenses**: Sum of all registered expenses.
  
- **Buttons**:
  - **Add Income**: Redirects to the transaction registration screen for incomes.
  - **Add Expense**: Redirects to the transaction registration screen for expenses.
  - **View Transactions**: Redirects to the detailed transaction list.

- **Clickable Views**:
When clicked, redirects to a new activity where a **pie chart** is shown, displaying the breakdown of all expense transactions.

### 2. Transaction Registration Screen
- Allows users to register income and expense transactions with the following fields:
  - **Title**: Name of the transaction (required).
  - **Amount**: Value of the transaction (required).
  - **Category**: Type of transaction (e.g., Food, Transport, Salary, etc.).
  - **Date**: Date of the transaction (optional, using a DatePicker).

- **Buttons**:
  - **Save**: Saves the transaction to the database.
  - **Cancel**: Returns to the main screen without saving the transaction.

### 3. Transaction List Screen
- Displays a list of all registered transactions, organized by date (using a `RecyclerView`).
- Each list item shows:
  - **Transaction Title**.
  - **Amount**: Positive for income and negative for expenses.
  - **Category** and **Date**.
  
- **Long Click Actions**:
  - **Edit**: Allows editing the transaction.
  - **Remove**: Shows a confirmation `AlertDialog` to remove the transaction.

### 4. Data Persistence
- Transactions are stored in a **Room Database**, ensuring data persistence across app sessions.

### 5. Performance Charts
- Uses **AnyChart** to display visual charts for financial performance, such as total income vs. expenses.
- **Pie Chart Views**: Provides interactive pie charts for viewing the distribution of transactions:
  - **All Transactions Pie Chart**: Shows the breakdown of all income and expense transactions.
  - **Income Pie Chart**: Shows the breakdown of income transactions.
  - **Expenses Pie Chart**: Shows the breakdown of expense transactions.

### 6. Daily Notifications
- Configures **WorkManager** to send daily notifications reminding the user to register their daily transactions.

## How to Run the Project

### Requirements
- Android Studio (or any IDE with Android support).
- Android Emulator or a physical device for testing.

### Steps to run
1. Clone this repository:
   ```bash
   git clone https://github.com/Sengeki1/Financial_Control_App.git
