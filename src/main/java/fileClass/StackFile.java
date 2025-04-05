package fileClass;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        String basePath = System.getProperty("user.home") + "/Akinator/data";
        File file = new File(basePath, "tree.ser");

        // Leer el archivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    QAndA qAndA = (QAndA) ois.readObject();
                    if (qAndA.isCharacter()) {
                        System.out.println(qAndA.getCharacterName());
                    }
                    lineProcess(qAndA);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
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
            //System.out.println(akiStack.peek().right.getData().getCharacterName());
            return akiStack.peek();
    }  
}
