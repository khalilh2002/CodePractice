public class Noeud{
    String str ;
    Noeud filsGauche, filsDroit ;
    Noeud (String s, Noeud fg, Noeud fd)
    {
        str = s ;
        filsGauche = fg ;
        filsDroit = fd ;
    }
}