
/* 
    Question 1  
    PGDC de 9 et 6 est 3 
*/

public class PGCD {
    public static void main(String[] args) throws Exception {
        
        int x=-24 , y=-12 ,pg = 1;
        
        

        /*Question 2 */
        for (int i = 1; i < y; i++) {
            if (x % i == 0 && y % i == 0) {
                pg=i;
            }
        }
        System.out.println(pg);
        
        /*Question 3 */
        System.out.println(pgcd(x, y));
       
    }
    
    public static int pgcd(int a , int b){
        int x = a > b ? a : b ;
        int y = a > b ? b : a ; 

            if (y > 0) {
                return pgcd(y, x % y);
            }else if (y==0){
                return x;
            }else{
                System.out.println("error le nombre est negatif");
                return -1; // error si nombre est negative
            }
        }
    }
    

