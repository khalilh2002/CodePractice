public class Dico
{
    Noeud racine ;
    Dico(){
        racine = null ;
    }

    public static void inserer (Dico d, String m){
        d.racine = insererArbre (d.racine, m) ;
    }

    static Noeud insererArbre (Noeud n, String m){

        if (n == null) {
            return new Noeud (m, null, null) ;
        }
        if (m.compareTo (n.str) == 0) {
            return n ; // le mot est deja la
        }
        if (m.compareTo (n.str) < 0){
            n.filsGauche = insererArbre (n.filsGauche, m) ;
        }
        else {
            n.filsDroit = insererArbre (n.filsDroit, m) ;
        }
        return n ;
    }

    public static boolean existe (Dico d, String m){
        if (d.racine.str.compareTo(m)==0) {
            return true;
        }

        Noeud tmp = d.racine;
        while (tmp!=null) {
            if (tmp.str.compareTo(m) == 0) {
                return true;
            }
            if (tmp.str.compareTo(m)>0) {
                tmp = tmp.filsGauche;
                
            }else if (tmp.str.compareTo(m) <0) {
                tmp = tmp.filsDroit;
            }
        }
        return false;
    }
}