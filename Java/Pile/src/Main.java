public class Main {
    public static void main(String[] args) throws Exception {
        HanoiJeux jeux = new HanoiJeux();
        jeux.remplireSRC(5);        
        
       
        //System.out.println("================================================");
        jeux.hanoi(5,jeux.SRC,jeux.DST,jeux.AUX);
        System.out.println("_________________________________________________________________");
        jeux.showPile();
    }
}    
