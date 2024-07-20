import java.util.ArrayList;

/* 
import java.util.ArrayDeque;

public class Pile {

    private ArrayDeque<Integer> stack;

    public Pile() {
        stack = new ArrayDeque<>();
    }

    public String ToString(){
        String s="[ ";
        for (Integer integer : stack) {
            s = s + integer + " , ";
        }
        s = s + " ]";
        return s;
    }

    public void push(int element) {
        stack.push(element);
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return stack.pop();
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

*/

public class Pile {

    int sommet=-1;
    ArrayList<Integer>pile;

    Pile(){
        pile = new ArrayList<Integer>();
    }
    public void push(int number){
        pile.add(number);
        sommet++;
    }
    public int pop(){
        if (sommet==-1) {
           // System.out.println("le pile est vide");
            
            return -1;
        }else{
            sommet--;
            return pile.removeLast();
        }
    }
    public void ToString(){
        if (sommet==-1) {
            System.out.println("|   |");

        }
        for (int i = sommet; -1 < i; i--) {
            System.out.println("| "+pile.get(i)+" |");
        }
        
    }


}
