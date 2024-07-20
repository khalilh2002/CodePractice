import static java.lang.StringTemplate.STR;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    int nombreComptes;
    //int nombreMax;
    ArrayList<CompteBancaire> comptes;

    Scanner sc = new Scanner(System.in);

    Bank(){ //int nombre
        //nombreMax= nombre;
        comptes = new ArrayList<CompteBancaire>();
        nombreComptes = 0;
    }

  

    void CreeCompte(){
        /*
        if (nombreComptes >= nombreMax) {
            System.out.println("Impossible");
        }else{
            comptes[nombreComptes] = new CompteBancaire();
            nombreComptes++;
        }*/

        comptes.add(new CompteBancaire());

    }
    void CreeCompte(String name){
        /*
        if (nombreComptes >= nombreMax) {
            System.out.println("Impossible");
        }else{
            comptes[nombreComptes++] = new CompteBancaire(name);
        }
        */
        
        comptes.add(new CompteBancaire(name));
    }
    void afficher(){
        /*for (int i = 0; i < nombreComptes; i++) {
            System.out.println(comptes[i].displayAccountInfo());
        }*/

        for (CompteBancaire compte : comptes) {
            System.out.println(compte.displayAccountInfo());
        }
    }
    
    CompteBancaire Search_Return_Compte(String name){
        for (CompteBancaire account : comptes) {
            if (account.getProprietor().equals(name)) {
                return account;
            }
        }
        return null;
    }
    
    int Search_Return_Index(String name){
        /*for (int i = 0; i < comptes.length; i++) {
            if (comptes[i].getProprietor().equals(name) ) {
                return i;
            }
        }
        return -1;*/
        for (int i = 0; i < comptes.size(); i++) {
            if (comptes.get(i).getProprietor().equals(name)) {
                return i;
            }   
        }
        return -1;
    }

    void afficherCompte(String name){
        int index = Search_Return_Index(name);
        if (index==-1) {
            System.out.println("le compte n'existe pas");
        }else{
            comptes.get(index).displayAccountInfo();
        }
    }
    char menu(){
        System.out.println("----------------Bank Menu-------------------");
        System.out.println("c: View bank Information");
        System.out.println("d: open account");
        System.out.println("w: create account");
        System.out.println("q: Quit");
        System.out.println("-----------------------------------------------");
        char choix = sc.next().charAt(0);
        return choix;
    }
    void operation(){
        
        boolean turnOn = true;
        String name;char choix ;
        while (turnOn) {
            choix = menu();
            
            switch (choix) {
                case 'c':
                    afficher();
                    break;
                case 'd':
    
                    System.out.println("donner le nom de compte");
                    name = sc.next();
                    int index = Search_Return_Index(name);
                    if (index==-1) {
                        System.out.println("OMG its baad");
                    }
                    comptes.get(index).performOperations();
    
                    break;
                case 'w':
                    System.out.println("donner le nom de neveaux compte");
                    name = sc.next();
                    CreeCompte(name);

                    break;  
    
                case 'q':
                    turnOn = false;
                    break;
                default:
                    break;
            }
            
        }

        
        

    }
}

