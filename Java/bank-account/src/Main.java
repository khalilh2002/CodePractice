import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Bank b1 = new Bank();
        b1.CreeCompte("khalil");
        b1.CreeCompte("ahmed");
        b1.CreeCompte("latifa");
        b1.operation();
    }
}