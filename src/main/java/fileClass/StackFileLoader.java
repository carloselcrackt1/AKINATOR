package fileClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Carlos Auqui
 */

 class Nodo {
    String data;
    Nodo left, right;
    
    public Nodo(String data) {
        this.data = data;
    }
}

public class StackFileLoader{
    private Stack<Nodo> akiStack;
    
    public StackFileLoader() {
        this.akiStack = new Stack<>();
    }
    
    public void fileReader(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                lineProcess(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWr(ArrayList<String> treeList, String fileName){
        try{
            File fl = new File(fileName);
            FileWriter fwr = new FileWriter(fl,false);
            for(int i = 0; i<treeList.size(); i++){
                fwr.write(treeList.get(i) + "/n");
            }
            fwr.close();
        }catch(IOException t){
            System.out.println(t.getMessage());
        }
    }
    // separa nodos preguntas de personajes
    private void lineProcess(String line) {
        if (line.contains("?")) { 
            Nodo question = new Nodo(line);
            if (!akiStack.isEmpty()) 
                question.right = akiStack.pop();
            if (!akiStack.isEmpty()) 
                question.left = akiStack.pop();
            akiStack.push(question);
        } else { // Es un personaje
            akiStack.push(new Nodo(line));
        }
    }
    
    public Nodo getAkiStackNode() {
        if (akiStack.isEmpty())
            return null;
        else
            return akiStack.peek();
    }
    
    public void SoutTree(Nodo nodo, String prefix) {
        if (nodo != null) {
            System.out.println(prefix + nodo.data);
            SoutTree(nodo.left, prefix + "  ");
            SoutTree(nodo.right, prefix + "  ");
        }
    }
}
