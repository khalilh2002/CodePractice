import java.util.Scanner;

public class produit {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        
        int resultat = multiplication(0,7);
        System.out.println(resultat);
    }

    public static int multiplication(int nb , int mult){
        if (nb>0 && mult > 1) {

            mult--;
            return nb+ multiplication(nb , mult);

        }else if (mult==1) {

            return nb;

        }else if(mult==0){
            
            return 0;
        }

        return 0;
    }
}
