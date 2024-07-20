import java.util.ArrayList;

public class flotte {
    ArrayList<vechule> list;
    flotte(  ){
        list = new ArrayList<vechule>();
    }
    void ajouter (vechule obj){
        list.add(obj);   
    }
    vechule chercher(String name_employer){
        for (vechule v : list) {
            if(v.nom_employe.compareTo(name_employer)==0){
                return v;
            }
        }
        return null;
    }
    vechule chercher(int matr){
        for (vechule v : list) {
            if(v.matricule==matr){
                return v;
            }
        }
        return null;
    }

    void lister(){
        for(var v : list){
            System.out.println(v);
        }
    }
}
