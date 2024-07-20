import java.util.Scanner;

public class CompteBancaire {

    private static Scanner scanner = new Scanner(System.in);

    private String proprietor;
    private double balance;
    private int accountNumber;
    private static int numberOfAccounts = 0;

    CompteBancaire() {
        this.proprietor = "None";
        balance = 0;
        accountNumber = numberOfAccounts++;
    }

    CompteBancaire(String proprietor) {
        this.proprietor = proprietor;
        balance = 0;
        accountNumber = numberOfAccounts++;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getProprietor() {
        return this.proprietor;
    }

    public static int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public String displayAccountInfo() {
        String info = "Owner: " + this.proprietor + " ";
        info += "Balance: " + this.balance + " ";
        info += "Account Number: " + this.accountNumber + " ";

        return info;
    }

    public void deposit(double amount) {
        if (amount >= 0) {
            this.balance += amount;
        }
    }

    public void deposit() {
        int retry = 0;
        while (retry < 3) {
            System.out.println("Enter the deposit amount: ");
            double amount = scanner.nextDouble();
            if (amount >= 0) {
                this.balance += amount;
                break;
            } else {
                System.out.println("Amount should be greater than or equal to 0.");
            }
            retry++;
        }
    }

    public boolean withdraw() {
        System.out.println("Enter the withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (amount >= 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Withdrawal not possible!");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount >= 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(CompteBancaire recipient, double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            recipient.balance += amount;
            return true;
        } else {
            System.out.println("Transfer not possible!");
            return false;
        }
    }

    private char accountMenu() {
        char choice;
        System.out.println("----------------Account Menu-------------------");
        System.out.println("c: View Account Information");
        System.out.println("d: Deposit");
        System.out.println("w: Withdraw");
        System.out.println("q: Quit");
        System.out.println("-----------------------------------------------");
        choice = scanner.next().charAt(0);

        return choice;
    }

    public void performOperations() {
        char choice;
        while (true) {
            choice = accountMenu();
            switch (choice) {
                case 'c':
                    System.out.println("______________________________________________________________");
                    System.out.println(this.displayAccountInfo());
                    System.out.println("_______________________________________________________________");
                    break;
                case 'd':
                    System.out.println("____________________________________________________________");
                    this.deposit();
                    System.out.println("______________________________________________________________");
                    break;
                case 'w':
                    System.out.println("________________________________________________________________");
                    this.withdraw();
                    System.out.println("_________________________________________________________________");

                    break;
                case 'q':
                    return;
                default:
                    System.out.println("_________________________________________________________");

                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("_____________________________________________________________");

                    break;
            }
        }
    }
}
