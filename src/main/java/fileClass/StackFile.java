package fileClass;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Stack;
import nodeG.node;
import questionAnswerClass.QAndA;

public class StackFile {
    private Stack<node<QAndA>> akiStack;
    
    public StackFile() {
        this.akiStack = new Stack<>();
    }
    
    public void fileReader() {
        InputStream input = getClass().getResourceAsStream("/data/tree.ser");
        try (ObjectInputStream ois = new ObjectInputStream(input)) {

            while (true) {
                try {
                    QAndA qAndA = (QAndA) ois.readObject();
                    lineProcess(qAndA);
                } catch (EOFException e) {
                    break;
                }
            }
       }catch(IOException e){
           e.printStackTrace();
       }catch(ClassNotFoundException e){
           e.printStackTrace();   
       }
    }
    
    private void lineProcess(QAndA qAndA) {
        if (!qAndA.isCharacter()) { 
            node<QAndA> question = new node(qAndA);
            if (!akiStack.isEmpty()) 
                question.right = akiStack.pop();
            if (!akiStack.isEmpty()) 
                question.left = akiStack.pop();
            akiStack.push(question);
        } else { //  personaje
            node<QAndA> student = new node(qAndA);
            akiStack.push(student);
        }
    }
    
    public node<QAndA> getAkiStackNode() {
        if (akiStack.isEmpty())
            return null;
        else
            return akiStack.peek();
    }  
}
