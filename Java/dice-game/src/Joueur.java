public  class Joueur {
    /*  ATTRIBUTS */
    private    String nom = new String();
    private    double score ;

    public Joueur(){
        this.nom="";
        this.score=0;
    }
    public Joueur( String nom ){
        this.nom=nom;
        this.score=0;
    } 
    public Joueur( String nom , Double score){
        this.nom=nom;
        this.score=score;
    }



    public String get_nom() {
        return this.nom;
    }
    
    public double get_score() {
        return this.score;
    }

    

    public void set_attributs(String nom , double score){
        this.nom=nom;
        this.score=score;
    }

    public void set_nom(String nom){
        this.nom=nom;
    }

    public void set_score(double score){
        this.score = score;
    }




    public void addscore(double x){
        this.score+=x;
    }

    public void afficher(){
        System.out.println("le nom de joueur : "+this.nom+" le score : "+this.score);
    }
     
}