
import java.util.*;

class BankAccount {

    private String accountHolder;
    private String accountNumber;
    private float balance;
    private int pin;

    ArrayList<String> history = new ArrayList<>();

    public BankAccount(String accountHolder,
            String accountNumber,
            float balance,
            int pin) {

        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public float getBalance() {
        return balance;
    }

    public String getName() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(float amount) {

        balance += amount;

        history.add("Deposited Rs." + amount);

        System.out.println("Money Deposited Successfully");
    }

    public void withdraw(float amount) {

        if (amount > balance) {

            System.out.println("Insufficient Balance");
            return;
        }

        balance -= amount;

        history.add("Withdraw Rs." + amount);

        System.out.println("Money Withdrawn Successfully");
    }

    public void transfer(float amount, Integer AccNum,
                         String receiver) {

        if (amount > balance) {

            System.out.println("Insufficient Balance");
            return;
        }

        balance -= amount;

        history.add("Transferred Rs." + amount +
                " To " + receiver);

        System.out.println("Transfer Successful");
    }

    public void changePin(int oldPin,
            int newPin) {

        if (oldPin == pin) {

            pin = newPin;

            history.add("PIN Changed");

            System.out.println("PIN Changed Successfully");
        } else {

            System.out.println("Incorrect Old PIN");
        }
    }

    public void showMiniStatement() {

        System.out.println("\n===== MINI STATEMENT =====");

        if (history.isEmpty()) {

            System.out.println("No Transactions Found");
            return;
        }

        for (String transaction : history) {

            System.out.println(transaction);
        }
    }
}

class ATM {

    private Scanner sc = new Scanner(System.in);

    private BankAccount account;

    private int loginAttempts = 0;

    public ATM(BankAccount account) {

        this.account = account;
    }

    public void authenticate() {

        while (loginAttempts < 3) {

            System.out.print("Enter PIN : ");

            int enteredPin = sc.nextInt();

            if (enteredPin == account.getPin()) {

                System.out.println(
                        "\nWelcome "
                                + account.getName());

                menu();

                return;
            }

            loginAttempts++;

            System.out.println(
                    "Wrong PIN. Attempts Left : "
                            + (3 - loginAttempts));
        }

        System.out.println(
                "\nATM Card Blocked.");
    }

    private void menu() {

        while (true) {

            System.out.println("\n========== ATM ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer Money");
            System.out.println("5. Change PIN");
            System.out.println("6. Mini Statement");
            System.out.println("7. Fast Cash");
            System.out.println("8. Account Details");
            System.out.println("9. Exit");

            System.out.print("Choose : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println(
                            "Balance : Rs."
                                    + account.getBalance());
                    break;

                case 2:

                    System.out.print(
                            "Enter Amount : ");

                    float withdrawAmount = sc.nextFloat();

                    account.withdraw(
                            withdrawAmount);

                    break;

                case 3:

                    System.out.print(
                            "Enter Amount : ");

                    float depositAmount = sc.nextFloat();

                    account.deposit(
                            depositAmount);

                    break;

                case 4:

                    System.out.print(
                            "Receiver Name : ");

                    sc.nextLine();

                    String receiver = sc.nextLine();

                    System.out.print(
                            "Amount : ");

                    float amount = sc.nextFloat();
                    System.out.print(
                            "Account number : ");

                    Integer Account = sc.nextInt();

                    account.transfer(
                            amount, Account,
                            receiver);

                    break;

                case 5:

                    System.out.print(
                            "Old PIN : ");

                    int oldPin = sc.nextInt();

                    System.out.print(
                            "New PIN : ");

                    int newPin = sc.nextInt();

                    account.changePin(
                            oldPin,
                            newPin);

                    break;

                case 6:
                    account.showMiniStatement();
                    break;

                case 7:

                    account.withdraw(500);

                    break;

                case 8:

                    System.out.println(
                            "\nAccount Holder : "
                                    + account.getName());

                    System.out.println(
                            "Account Number : "
                                    + account.getAccountNumber());

                    break;

                case 9:

                    System.out.println(
                            "Thank You For Using ATM");

                    return;

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }
}

public class atm_machine {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(
                "Divyanshu Patel",
                "1234567890",
                10000,
                9336);

        ATM atm = new ATM(account);

        atm.authenticate();
    }
}