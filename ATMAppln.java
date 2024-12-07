import java.util.*;
import java.io.*;

class Accounts {

    private String AccNo;
    private String PinNo;
    public double balance;

    public Accounts(String AccNo, String PinNo, double balance) {
        this.AccNo = AccNo;
        this.PinNo = PinNo;
        this.balance = balance;
    }

    public String AccNo() {
        return AccNo;
    }

    public String PinNo() {
        return PinNo;
    }

    public double getBalance() {
        return balance;
    }

    public void depositA(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited amount is Rs. " + amount);
        } else {
            System.out.println("Invalid amount");
        }
    }

    public void withdrawA(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("The withdrawn amount is Rs: " + amount);
        } else {
            System.out.println("Invalid amount");
        }
    }
}

class ATM {

    HashMap<String, Accounts> account = new HashMap<>();
    private Accounts currentacc;
    private Scanner sc = new Scanner(System.in);

    public ATM() {
        account.put("123456", new Accounts("123456", "543", 1000));
        account.put("789456", new Accounts("789456", "231", 2000));
    }

    public void start() {
        System.out.println("WELCOME TO ATM");
        if (!authenticate()) {
            System.out.println("Invalid account or password. Exiting.");
            return;
        }
        int choice;
        do {
            System.out.println("\n1. Check Balance\n2. Deposit Amount\n3. Withdraw Amount\n4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Enter a valid choice.");
                    break;
            }
        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.println("Your current balance is: Rs. " + currentacc.getBalance());
    }

    private void deposit() {
        System.out.print("Enter the amount you want to deposit: ");
        double amount = sc.nextDouble();
        currentacc.depositA(amount);
    }

    private void withdraw() {
        System.out.print("Enter the amount you want to withdraw: ");
        double amount = sc.nextDouble();
        currentacc.withdrawA(amount);
    }

    private boolean authenticate() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter your account number: ");
            String accNo = sc.next();
            System.out.print("Enter your PIN: ");
            String pin = sc.next();
            Accounts acc = account.get(accNo);
            if (acc != null && acc.PinNo().equals(pin)) {
                currentacc = acc;
                System.out.println("Authentication successful!");
                return true;
            } else {
                System.out.println("Invalid account number or PIN. Please try again.");
            }
            attempts++;
        }
        return false;
    }
}

public class ATMAppln {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
