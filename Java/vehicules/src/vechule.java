public class vechule {
    int count = 0; 

    int matricule;
    String nom_employe;
    String etat;
    vechule(){
        
    }

    void affecter (String nom_employe){
        this.nom_employe = nom_employe;
        this.matricule = count++;
        etat = "Service";
    }

    public String toString(){
        String t = "vechule\n";
        t+= "nom de employe : "+nom_employe+"\n";
        t += "matricule : "+matricule+"\n";
        t += "Etat :"+etat+"\n";

        return t;
    }
    

}
