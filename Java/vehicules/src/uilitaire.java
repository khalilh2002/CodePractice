public class uilitaire extends vechule{
    uilitaire(){
        super();
    }

    @Override
    public String toString(){
        String t = "Utilitaire\n";
        t+= "nom de employe : "+nom_employe+"\n";
        t += "matricule : "+matricule+"\n";
        t += "Etat :"+etat+"\n";

        return t;
    }
}
