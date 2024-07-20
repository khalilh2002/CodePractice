public class Main {
    public static void main(String args[]) {
        //on crée un jeu
        Joueur j1 = new Joueur(args[0]);
        Joueur j2 = new Joueur(args[1]);
        //la partie se fait sur 10 tirages
        Jeu game = new Jeu(j1,j2);
        game.jouer(10);
         
       }
      
}
