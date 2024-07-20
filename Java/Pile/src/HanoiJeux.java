public class HanoiJeux {
    Pile SRC , DST , AUX ;
    HanoiJeux(){
        SRC = new Pile();
        DST = new Pile();
        AUX = new Pile();   


    }
    void remplireSRC(int n){
        for (int i = n; i > 0; i--) {
            SRC.push(i);
        }
    }
    void showPile(){
        System.out.println("--------------------------------------");
        System.out.println("SRC");
        SRC.ToString();
        System.out.println("AUX");
        AUX.ToString();
        System.out.println("DST");
        DST.ToString();
        System.out.println("--------------------------------------");
    }
    void DeplacerUnDisque(Pile SC , Pile DC){
        int n = SC.pop();
        if (n!=-1) {
            DC.push(n);
        }
    }
    void hanoi(int N ,Pile S , Pile D ,Pile A ){
        if (N!=0) {
            hanoi(N-1 , S ,A ,D);  
            showPile();
            DeplacerUnDisque(S, D);
            showPile();
            hanoi(N-1, A, D, S);
            showPile();
        }
    }
}
