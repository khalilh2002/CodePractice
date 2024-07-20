public class voiture extends vechule {
    voiture(){
        super();
    }
    @Override
    public String toString(){
        String t = "voiture\n";
        t+= "nom de employe : "+nom_employe+"\n";
        t += "matricule : "+matricule+"\n";
        t += "Etat :"+etat+"\n";

        return t;
    }
    

}
