package fileClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import questionAnswerClass.Question;
import questionAnswerClass.Student;

/**
 *
 * @author Carlos Auqui
 */
public class StackFileLoader {
    private Stack <Node> akiStack = new Stack<>();

    public Stack <Node> getAkiStack() {
        return akiStack;
    }
     
    class Node<E>{
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data){
            this.data = data;
        }
    }
    
    public void fileLoader(String filename){
        try{
            FileReader akiFile = new FileReader("PersonajesyPreguntas.txt");
            BufferedReader br = new BufferedReader (akiFile); 
            while(br.readLine() != null){
                if(!br.readLine().contains("?")){
                    Node<Student> st = new Node(br.readLine().trim());
                    akiStack.push(st);
                }else{
                    Node<Question> qst = new Node(br.readLine().trim());
                    qst.right = akiStack.pop();
                    qst.left = akiStack.pop();
                    akiStack.push(qst);
                }
            }   
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    
    }
}
