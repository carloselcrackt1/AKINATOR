package fileClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import questionAnswerClass.Question;
import questionAnswerClass.Student;

/**
 *
 * @author Carlos Auqui
 */
public class StackFileLoader {
    private Stack <Node> akiStack = new Stack<>();

    class Node<E>{
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data){
            this.data = data;
        }
    }
    // funcion que va a devolver el stack con un solo nodo
    public void fileLoader(String fileName){
        try{
            FileReader akiFile = new FileReader(fileName);
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
            br.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    
    }
    // funcion que recibira una lista actualizada de los elementos.
    public void fileWr(ArrayList<String> listaArbol, String fileName){
        try{
            File fl = new File(fileName);
            FileWriter fwr = new FileWriter(fileName,false);
            for(int i = 0; i<listaArbol.size(); i++){
                fwr.write(listaArbol.get(i));
            }
            fwr.close();
        }catch(IOException t){
            System.out.println(t.getMessage());
        }
    }
    
    public Stack <Node> getAkiStack() {
        return akiStack;
    }
     
}
