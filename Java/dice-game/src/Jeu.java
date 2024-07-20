public class Jeu {
    Joueur joueur1, joueur2;
    public Jeu(Joueur joueur1 , Joueur joueur2) {
        this.joueur1=joueur1;
        this.joueur2=joueur2;
    }

    void tirage(Joueur S){
        Double D = Double.valueOf(6 * Math.random() + 1);
        int resultat=D.intValue();
        S.addscore(resultat);
    }

    void jouer(int n){
     //procéder à n tirages
        for (int i = 0; i < n; i++) {
            tirage(this.joueur1);
            tirage(this.joueur2);
        }
          
     //indiquer le gagnant
        if (this.joueur1.get_score() > this.joueur2.get_score()) {
            System.out.println("le gagnant---------------WINNER--------------");
            this.joueur1.afficher();
            
        }else if (this.joueur1.get_score() < this.joueur2.get_score()) {
            System.out.println("le gagnant---------------WINNER--------------");
            this.joueur2.afficher();
            
        }else{
            System.out.println("les deux sont egaux / Draw");

        }
         
    }
       
}
