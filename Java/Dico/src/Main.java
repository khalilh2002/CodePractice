public class Main {
    public static void main(String[] args){
        Dico d = new Dico();

        Dico.inserer(d,"f" );

        Dico.inserer(d,"c" );
        Dico.inserer(d,"b" );
        Dico.inserer(d,"a" );
        Dico.inserer(d,"d" );
        
        Dico.inserer(d,"e" );
        Dico.inserer(d,"g" );
        Dico.inserer(d,"h" );
        
        Dico.inserer(d,"j" );
        Dico.inserer(d,"l" );
        Dico.inserer(d,"k" );
        Dico.inserer(d,"m" );

        if (Dico.existe(d, "z")) {
            System.out.println("its existe");
        }else{
            System.out.println("not existe ");
        }
    }
    
}
