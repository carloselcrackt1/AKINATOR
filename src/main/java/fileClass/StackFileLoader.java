package fileClass;

import binaryTree.GenericArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Stack;
import nodeG.node;
import questionAnswerClass.QAndA;

/**
 *
 * @author Carlos Auqui
 */
/*

public class StackFileLoader<E> {
    private Stack<node<QAndA>> akiStack;
    
    public StackFileLoader() {
        this.akiStack = new Stack<>();
    }
        
ArrayList<QAndA> qAndAs = new ArrayList<>();
        InputStream input = getClass().getResourceAsStream("/data/tree.ser");
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            while (true) {
                try {
                    QAndA qAndA = (QAndA) ois.readObject();
                    qAndAs.add(qAndA);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
    public void fileReader(){
        InputStream input = getClass().getResourceAsStream("/data/tree.ser");
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            while (true) {
                try {
                    QAndA qAndA = (QAndA) ois.readObject();
                    qAndAs.add(qAndA);
                } catch (EOFException e) {
                    break;
                }
}
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
*/