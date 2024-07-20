import java.util.Scanner;

public class App {
    public static Scanner  sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        afficher();
    }

    public  static void afficher(){
        int x =sc.nextInt();
        if (x!=0) {
            afficher();
            System.out.print(x+" ");
        }else if(x==0){
            System.out.print(x+" ");
        }
    }
}
