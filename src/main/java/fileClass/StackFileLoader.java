package fileClass;

import binaryTree.GenericArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import nodeG.node;

/**
 *
 * @author Carlos Auqui
 */


public class StackFileLoader<E>{
    private Stack<node<E>> akiStack;
    
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

    public void fileWr(GenericArrayList<E> treeList, String fileName){
        try{
            File fl = new File(fileName);
            FileWriter fwr = new FileWriter(fl,false);
            for(int i = 0; i<treeList.size(); i++){
                fwr.write(treeList.get(i) +"\n");
            }
            fwr.close();
        }catch(IOException t){
            System.out.println(t.getMessage());
        }
    }
    // separa nodos preguntas de personajes
    private void lineProcess(String line) {
        if (line.contains("?")) { 
            node<E> question = new node(line);
            if (!akiStack.isEmpty()) 
                question.right = akiStack.pop();
            if (!akiStack.isEmpty()) 
                question.left = akiStack.pop();
            akiStack.push(question);
        } else { //  personaje
            node<E> student = new node(line);
            akiStack.push(student);
        }
    }
    
    public node<E> getAkiStackNode() {
        if (akiStack.isEmpty())
            return null;
        else
            return akiStack.peek();
    }    
}
